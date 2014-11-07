import java.util.concurrent.Exchanger

Exchanger<String> exchanger = new Exchanger<>()

def thr = new Runnable() {
    @Override
    void run() {
        println exchanger.exchange(Thread.currentThread().getName())
    }
}

new Thread(thr).start()
new Thread(thr).start()