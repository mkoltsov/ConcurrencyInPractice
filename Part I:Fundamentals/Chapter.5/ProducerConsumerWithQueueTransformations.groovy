import groovy.transform.TupleConstructor

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

@TupleConstructor
public class FileReaderProducer implements Runnable {
    private BlockingQueue<Expando> queue

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

@TupleConstructor
public class FilePublisherConsumer implements Runnable {
    private BlockingQueue<Expando> queue

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
new Thread(new FileReaderProducer(queue: queue)).start()

(1..Runtime.getRuntime().availableProcessors()).each { new Thread(new FilePublisherConsumer(queue: queue)).start() }

