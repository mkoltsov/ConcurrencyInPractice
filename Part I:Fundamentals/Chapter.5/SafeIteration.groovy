def ab = new Runnable() {
    private final Vector vec = [1, 2, 3, 4]

    @Override
    void run() {
        synchronized (vec) {
            println Thread.currentThread().getName()
            vec.each { _ -> println _ }
        }
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