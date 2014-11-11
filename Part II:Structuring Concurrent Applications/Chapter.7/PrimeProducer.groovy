import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

class Task implements Runnable {
    private final BlockingQueue<Integer> queue
    private AtomicInteger curr = new AtomicInteger(0)

    Task(queue) {
        this.queue = queue
    }

    @Override
    void run() {
        try {
            while (!Thread.currentThread().interrupted) {
                queue.put(curr.addAndGet(1))
            }
        }
        catch (InterruptedException ex) {
            println "i was interrupted!!!"
        }
    }

    public static void interrupt() {
        Thread.currentThread().interrupt()
    }

}

def queue = new LinkedBlockingQueue<Integer>(100000000)
def task = new Task(queue)
def exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
exec.with {
    execute(task)
    execute(task)
    execute(task)
    execute(task)
}
try {
    sleep 500
} finally {
    exec.shutdownNow()
//    Task.interrupt()
}
println queue
