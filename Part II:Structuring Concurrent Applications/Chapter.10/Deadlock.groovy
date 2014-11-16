import java.util.concurrent.Executors

class DeadBone {
    private final Object left = new Object()
    private final Object right = new Object()

    def doSomething() {
        synchronized (left) {
            synchronized (right) {
                println "inside block1"
            }
        }
    }

    def doSomething2() {
        synchronized (right) {
            synchronized (left) {
                println "inside block2"
            }
        }
    }
}

def deadMeat = new DeadBone()
Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).with {
    execute(new Runnable() {
        @Override
        void run() {
           println Thread.currentThread().getName() + "has started"
            deadMeat.doSomething()
        }
    })
    execute(new Runnable() {
        @Override
        void run() {
            println Thread.currentThread().getName() + "has started"
            deadMeat.doSomething2()
        }
    })
    execute(new Runnable() {
        @Override
        void run() {
            println Thread.currentThread().getName() + "has started"
            deadMeat.doSomething()
        }
    })
    execute(new Runnable() {
        @Override
        void run() {
            println Thread.currentThread().getName() + "has started"
            deadMeat.doSomething2()
        }
    })
}