def ab = new Runnable() {
    private final java.util.concurrent.ConcurrentSkipListSet<String> map = ["Moscow", "Podolsk", "Kiev", "Lisboa", "London"]

    @Override
    void run() {
        map.add("City"+new Random().nextInt(100))
        map.each { _ -> println _ }
        println map.size()
    }
}
new Thread(ab).start()
new Thread(ab).start()
