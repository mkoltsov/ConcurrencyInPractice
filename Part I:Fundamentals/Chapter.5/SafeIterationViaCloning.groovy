def ab = new Runnable() {
    private final Vector vec = [1, 2, 3, 4]

    @Override
    void run() {
        def clone;
        synchronized (vec) {
            clone = new Vector(vec)
            println Thread.currentThread().getName()
        }
        clone.each { _ -> println _ }
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