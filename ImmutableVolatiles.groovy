class ImmutableHolder {
    final Integer value;

    ImmutableHolder(val) {
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
    private volatile ImmutableHolder cache = new ImmutableHolder(null)

    @Override
    void run() {
        int val = new Random().nextInt(10)
        synchronized (this) {
            if (cache.getValue() == null) {
                cache = new ImmutableHolder(val)
                println "put $val to cache"
            } else {
                println "$val is in cache"
            }
        }
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