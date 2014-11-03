class ImmutableHolder {
    final Integer value;

    ImmutableHolder(val) {
        this.value = val
        println val
    }

    int getValue() {
        new Integer(value)
    }
}


def imutHoldPenetrator = new Runnable() {
    private volatile ImmutableHolder cache = new ImmutableHolder(0)

    @Override
    void run() {
        int val = new Random().nextInt(10)
        println cache.getValue()
        if (cache.getValue() == 0) {
            cache = new ImmutableHolder(val)
//            println "put $val to cache"/
        }
//        else {
//
//
//
//        }
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