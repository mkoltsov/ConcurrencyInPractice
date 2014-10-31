def ab = new Runnable() {
    volatile int a;

    synchronized void add() {
        a++
    }

    @Override
    void run() {
        println Thread.currentThread().name
        add()
        println a
    }
}
def t1 = new Thread(ab)
def t2 = new Thread(ab)
t1.start()
t1.join()
t2.start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()
//new Thread(ab).start()