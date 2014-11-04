def ab = new Runnable() {
    volatile int a;
//produces race condition
//    synchronized
    void add() {
        a++
    }

    @Override
    void run() {
        println Thread.currentThread().name
        add()
        println a
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