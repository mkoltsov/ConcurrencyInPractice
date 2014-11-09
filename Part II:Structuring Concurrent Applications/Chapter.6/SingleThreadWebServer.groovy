import java.util.concurrent.Executor
import java.util.concurrent.Executors

ServerSocket socket = new ServerSocket(80)
Executor executor = Executors.newSingleThreadExecutor()
while (true) {
    Runnable task = new Runnable() {
        @Override
        void run() {
            Socket connection = socket.accept()
            println Thread.currentThread().getName() + connection
        }
    }
    executor.execute(task)
}