import java.util.concurrent.CyclicBarrier

class Task {
    final List subTasks = ['eat', 'sleep', 'rave', 'repeat']

    public static taskCompleted(thr) { println 'Job has been done by ' + thr }

}

def cpuCount = Runtime.getRuntime().availableProcessors()
def curr = { Thread.currentThread().getName() }

final CyclicBarrier barrier = new CyclicBarrier(cpuCount, new Runnable() {
    @Override
    void run() {
        Task.taskCompleted(curr())
    }
})

class Worker implements Runnable {
    private final Task task
    private final CyclicBarrier barrier

    public Worker(task, barrier) {
        this.task = task
        this.barrier = barrier
    }


    def computeValue = {
        sleep 500
        def random = { new Random().nextInt(4) }
        println "Computing the value: " + task.subTasks[random()] + " by " + Thread.currentThread().getName()
    }

    @Override
    void run() {
        computeValue()
        try {
            barrier.await()
        } catch (Exception ex) {
            ex.printStackTrace()
        }
    }
}

(1..cpuCount).each { int i -> new Thread(new Worker(new Task(), barrier)).start() }