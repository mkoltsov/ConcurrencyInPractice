import java.util.concurrent.atomic.AtomicReference

class Publ {
    final Integer value;

    Publ(val) {
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
//    private volatile Publ shared = null
    private AtomicReference<Publ> shared = new AtomicReference<>(new Publ(31337))

    @Override
    void run() {
       println shared.get().getValue()
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