import groovy.transform.TupleConstructor

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@TupleConstructor
public class FileReaderProducer2 implements Runnable {
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
public class FilePublisherConsumer2 implements Runnable {
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
def start = System.currentTimeMillis()
Executors.newSingleThreadExecutor().execute(new FileReaderProducer2(queue: queue))
def consumer = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors())
(1..Runtime.getRuntime().availableProcessors()).each { consumer.execute(new FilePublisherConsumer2(queue: queue)) }
Runtime.getRuntime().addShutdownHook(new Thread() {
    @Override
    void run() {
        println System.currentTimeMillis() - start
    }
})
