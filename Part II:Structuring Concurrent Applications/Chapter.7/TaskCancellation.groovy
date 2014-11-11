import java.util.concurrent.Executors

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
Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).with {
    execute(task)
    execute(task)
    execute(task)
    execute(task)
}
try {
    sleep new Random().nextInt(1000)
} finally {
    task.cancel()
}
println task.get()
