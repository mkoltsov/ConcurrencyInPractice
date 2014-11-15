import java.util.concurrent.Executors

def deadBone = new Runnable() {
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

    @Override
    void run() {
        println("i'm in")
    }
}

Executors.newCachedThreadPool().with {
    execute(deadBone.doSomething())
    execute(deadBone.doSomething2())
}