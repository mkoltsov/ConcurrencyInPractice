import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.Executors
import java.util.concurrent.Future

Executor exec = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors())
def nTasks = 1000
java.util.concurrent.CompletionService<Double> completionService = new ExecutorCompletionService<Double>(exec)
(0..nTasks).each {
    completionService.submit(new Callable<Double>() {
        @Override
        Double call() throws Exception {
            sleep 1500
            return new Random().nextGaussian()
        }
    })
}

try {
    (0..nTasks).eachWithIndex { name, index ->
        Future<Double> f = completionService.take()
        println "task #$index, value:" + f.get()
    }
} catch (Exception ex) {
    ex.printStackTrace()
}
