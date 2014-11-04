import java.util.concurrent.ArrayBlockingQueue

class Pub {
    final Integer value;

    public Pub(val) {
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
    private ArrayBlockingQueue<EPub> shared = new ArrayBlockingQueue<>(1, true, Arrays.asList(new EPub(31337)))

    @Override
    void run() {
        println shared.peek().getValue()
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