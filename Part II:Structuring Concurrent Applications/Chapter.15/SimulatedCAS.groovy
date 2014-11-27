import java.util.concurrent.Executors

class SimulatedCas {
    private int value

    synchronized int get() { return value }

    synchronized compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value
        if (oldValue == expectedValue) {
            value = newValue
            return oldValue
        }
    }

    synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return (expectedValue == compareAndSwap(expectedValue, newValue))
    }
}

def simCas = new SimulatedCas(value: 10)

def task9 = new Runnable() {
    def randomness = { new Random().nextInt(10) }

    @Override
    void run() {
        println simCas.compareAndSet(10, randomness())
    }
}

Executors.newCachedThreadPool().with {
    execute(task9)
    execute(task9)
    execute(task9)
    execute(task9)
    execute(task9)
}