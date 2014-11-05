import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

public class FileReaderProducer implements Runnable {
    private final BlockingQueue<Expando> queue

    FileReaderProducer(queue) {
        this.queue = queue
    }

    @Override
    void run() {
        new File("text.csv").splitEachLine(",", { line ->
            queue.put(new Expando(languageFrom: line[0],
                    languageTo: line[1],
                    meaningFrom: line[2],
                    meaningTo: line[3]))
        })
    }
}

public class FilePublisherConsumer implements Runnable {
    private final BlockingQueue<Expando> queue

    FilePublisherConsumer(queue) {
        this.queue = queue
    }

    @Override
    void run() {
        while (true) {
            def line = queue.take()
            line.printInfo = {
                println "{from:$languageFrom, to:$languageTo, is: $meaningFrom, counterpart:$meaningTo}"
            }
            line.printInfo()
        }
    }
}

BlockingQueue<Expando> queue = new ArrayBlockingQueue<>(50)
//BlockingQueue<Expando> queue = new LinkedBlockingQueue<>(50)
new Thread(new FileReaderProducer(queue)).start()

def availableThreads = [1..Runtime.getRuntime().availableProcessors()]
availableThreads.each { new Thread(new FilePublisherConsumer(queue)).start() }
