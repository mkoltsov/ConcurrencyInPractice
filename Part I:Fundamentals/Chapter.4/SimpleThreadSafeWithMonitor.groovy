public final class Counter2 {
    private long cnt

    public synchronized increment() {
        if (cnt == Long.MAX_VALUE) {
            throw new IllegalStateException("it's too much")
        }
        cnt++
    }
}

def ab = new Runnable() {
    Counter2 cnt = new Counter2()

    @Override
    void run() {
        def val = cnt.increment()
        println "new value is $val"
    }
}
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()