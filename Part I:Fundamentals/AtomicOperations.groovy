import java.util.concurrent.atomic.AtomicLong

def ab = new Runnable() {
    AtomicLong a = new AtomicLong(0);

    @Override
    void run() {
        println Thread.currentThread().name
        println a.incrementAndGet()
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