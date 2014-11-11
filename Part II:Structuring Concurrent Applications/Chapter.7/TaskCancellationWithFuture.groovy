import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

def task = new Runnable() {
    private final List<Integer> pseudoPrimesList = [1]
    private volatile boolean isCancelled

    @Override
    void run() {
        def p = Integer.parseInt("0")
        while (!isCancelled) {
            p = p + 1
            synchronized (this) {
                pseudoPrimesList.add(p)
            }
        }
    }

    public void cancel() {
        isCancelled = true
    }

    public synchronized List<Integer> get() {
        return new ArrayList<Integer>(pseudoPrimesList)
    }
}
def Future<?> cancel
Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).with {
    cancel = it.submit(task)
}
try {
    sleep new Random().nextInt(1000)
} finally {
    try {
        cancel.get(5, TimeUnit.SECONDS)
    } catch (Exception ex) {
        ex.printStackTrace()
    }
    finally {
        cancel.cancel(true)
    }
}
//println task.get()
