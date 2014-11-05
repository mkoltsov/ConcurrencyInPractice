def ab = new Runnable() {
    private final java.util.concurrent.ConcurrentHashMap<String, Integer> map = [Moscow:1, Podolsk:2, Kiev:3, Lisboa:4, London:5]

    @Override
    void run() {
        map.putIfAbsent("City"+new Random().nextInt(100), new Random().nextInt(100))
        map.each { _ -> println _ }
        println map.size()
    }
}
new Thread(ab).start()
new Thread(ab).start()
