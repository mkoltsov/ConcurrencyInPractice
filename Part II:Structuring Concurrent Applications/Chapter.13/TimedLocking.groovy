import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

def lock = new ReentrantLock()
def tName = { Thread.currentThread().getName() }

def task6 = new Runnable() {
    @Override
    void run() {
        def name = tName()
        try {
            lock.lock()
            println "$name has acquired the lock"
            sleep 100
        }
        finally {
            lock.unlock()
            println "$name has unlocked"
        }
    }
}

def task7 = new Runnable() {
    @Override
    void run() {
        def name = tName()
        println "started $name"
        if (!(lock.tryLock() || lock.tryLock(9, TimeUnit.MILLISECONDS))) {
            println "couldn't get the lock"
        } else
            try {
                println "$name has acquired the lock"
            }
            finally {
                println "$name has unlocked"
                lock.unlock()
            }
    }
}

Executors.newCachedThreadPool().with {
    execute(task6)
    execute(task7)
}