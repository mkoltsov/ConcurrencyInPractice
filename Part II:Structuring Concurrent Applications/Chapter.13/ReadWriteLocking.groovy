import java.util.concurrent.Executors
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class Task5 implements Runnable {
    private final Lock lock

    Task5(Lock lock) {
        this.lock = lock
    }

    @Override
    void run() {
        lock.lock()
        try {
            println Thread.currentThread().getName() + " has acquired the lock"
            sleep 500
        } finally {
            lock.unlock()
        }
    }
}
//fairness is not promised by the JMM!
//Consider not using it
def lock = new ReentrantLock(true)


Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+40).with {
   execute(new Task5(lock))
   execute(new Task5(lock))
   execute(new Task5(lock))
   execute(new Task5(lock))
   execute(new Task5(lock))
   execute(new Task5(lock))
   execute(new Task5(lock))
   execute(new Task5(lock))
   execute(new Task5(lock))
}