import groovy.transform.Canonical

import java.util.concurrent.Executors

@Canonical
class Task2 implements Runnable {
    def resource

    @Override
    void run() {
        for (; ;) {
            synchronized (resource) {
                println Thread.currentThread().getName()
                sleep 500
            }

        }
    }
}

Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).with {
    def resource = new Object()

    execute(new Task2(resource))
    execute(new Task2(resource))
    execute(new Task2(resource))
    execute(new Task2(resource))
}