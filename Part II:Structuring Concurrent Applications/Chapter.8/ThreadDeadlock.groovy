import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.Future

def task = new Callable<Integer>() {
    @Override
    Integer call() throws Exception {
        return new Random().nextInt()
    }
}

Executor exec = Executors.newSingleThreadExecutor()
Future<Integer> first, second
first = exec.submit(task)
second = exec.submit(task)
println first.get() + second.get()


