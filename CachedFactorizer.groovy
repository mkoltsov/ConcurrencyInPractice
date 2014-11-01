def ab = new Runnable() {
    private int lastCached = 0;

    synchronized int getLastCached() {
        return lastCached;
    }

    @Override
    void run() {
        int b = new Random().nextInt(2)
        synchronized (this) {
            if (b != lastCached) {
                lastCached = b;
            }
        }
        println getLastCached()
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