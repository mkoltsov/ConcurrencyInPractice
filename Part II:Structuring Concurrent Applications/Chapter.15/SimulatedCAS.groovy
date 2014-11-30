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

class CASCounter {
    private SimulatedCas value

    def get() {
        value.get()
    }

    def increment() {
        def i

        i = value.get()
        while (i != value.compareAndSwap(i, i + 1)) {
            i = value.get()
        }
        i + 1
    }
}

def simCas = new SimulatedCas(value: 10)
def casCnt = new CASCounter(value: simCas)

def task9 = new Runnable() {
    def randomness = { new Random().nextInt(10) }

    @Override
    void run() {
            println casCnt.increment()
    }
}

Executors.newCachedThreadPool().with {
    execute(task9)
    execute(task9)
    execute(task9)
    execute(task9)
    execute(task9)
}

