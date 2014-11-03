import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicReference

class EPub {
    final Integer value;

    public EPub(val) {
        this.value = val
    }

    Integer getValue() {
        if (value == null) {
            null
        } else {
            new Integer(value)
        }
    }
}


def imutHoldPenetrator = new Runnable() {
    private AtomicReference<EPub> shared = new AtomicReference<>(new EPub(new Random().nextInt(10000)))

    @Override
    void run() {

        def randomness = new Random().nextInt(100000)
        def last = shared.getAndSet(new EPub(randomness));
        def lastValue = last.getValue()
        println "last=$lastValue, new = $randomness"

    }
}

new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()
new Thread(imutHoldPenetrator).start()