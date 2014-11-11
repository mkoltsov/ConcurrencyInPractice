import groovy.transform.Canonical

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.Semaphore

class BoundExecutor {
    private final Semaphore semaphore
    private final Executor exec

    BoundExecutor(Executor executor, int bound) {
        exec = executor
        semaphore = new Semaphore(bound)
    }

    public void submitTask(Runnable task) {
        semaphore.acquire()
        try {
            exec.execute(new Runnable() {
                public void run() {
                    try {
                        task.run();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}

def ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

def boundEx = new BoundExecutor(ex, 1)
def task = (new Runnable() {
    @Override
    void run() {
        println Thread.currentThread().getName()
    }
})
boundEx.submitTask(task)
boundEx.submitTask(task)
boundEx.submitTask(task)
boundEx.submitTask(task)
boundEx.submitTask(task)
boundEx.submitTask(task)
boundEx.submitTask(task)
boundEx.submitTask(task)
boundEx.submitTask(task)