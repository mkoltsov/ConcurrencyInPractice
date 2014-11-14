import java.util.concurrent.Callable
import java.util.concurrent.CancellationException
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.FutureTask

/**
 * NOT IMPLEMENTED
 * @param < V >
 */
@Deprecated
abstract class BackgroundTask1<V> implements Runnable, Future<V> {
    private final FutureTask<V> computation = new Computation()
    private final Executor exec = Executors.newCachedThreadPool()
    private class Computation extends FutureTask<V> {

        Computation() {
            super(new Callable<V>() {
                @Override
                V call() throws Exception {
                    return BackgroundTask1.this.compute()
                }
            })
        }

        protected final void done() {
            exec.execute(new Runnable() {
                @Override
                void run() {
                    V value = null
                    Throwable thrown = null
                    boolean cancelled = false
                    try {
                        value = get()
                    } catch (ExecutionException e) {
                        thrown = e.getCause()
                    } catch (CancellationException e) {
                        cancelled = true
                    } catch (InterruptedException consumed) {
                    } finally {
                        onCompletion(value, thrown, cancelled)
                    }
                }
            }

            )
        }
    }

    protected abstract V compute()

    protected void setProgress(final int current, final int max) {
        exec.execute(new Runnable() {
            @Override
            void run() {
                onProgress(current, max)
            }
        })
    }

    protected void onCompletion(V v, Throwable throwable, boolean b) {}

    protected abstract void onProgress(int current, int max)

}
//
//def task = new BackgroundTask1<Void>() {
//
//    @Override
//    protected Void compute() {
//        return null
//    }
//
//    @Override
//    protected void onProgress(int current, int max) {
//
//    }
//}