def ab = new Runnable() {
    volatile boolean asleep

    @Override
    void run() {
        asleep = new Random().nextBoolean()
        while (!asleep) {
            println "randomness has returned false to me"
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