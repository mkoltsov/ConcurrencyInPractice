import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

ServerSocket socket = new ServerSocket(80)
Executor executor = Executors.newScheduledThreadPool(
        Runtime.getRuntime().availableProcessors())
while (true) {
    Runnable task = new Runnable() {
        @Override
        void run() {
            Socket connection = socket.accept()
            println Thread.currentThread().getName() + connection
        }
    }
    executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.MINUTES)
}