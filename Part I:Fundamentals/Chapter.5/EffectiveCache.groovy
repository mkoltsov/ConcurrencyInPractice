import java.util.concurrent.Callable
import java.util.concurrent.CancellationException
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Future

interface Computable<A, V> {
    V compute(A args);
}

class Memoizer<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new java.util.concurrent.ConcurrentHashMap<>()
    private final Computable<A, V> computable

    public Memoizer(Computable<A, V> computable) { this.computable = computable }

    @Override
    V compute(final A arg) {
        while (true) {
            Future<V> f = cache.get(arg)
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    V call() throws Exception {
                        computable.compute(arg)
                    }
                }
                java.util.concurrent.FutureTask<V> ft = new java.util.concurrent.FutureTask<>(eval)
                f = cache.putIfAbsent(arg, ft)
                if (f == null) {
                    f = ft
                    ft.run()
                }
                try {
                    return f.get()
                } catch (CancellationException e) {
                    cache.remove(arg, f)
                } catch (Exception ex) {
                    ex.printStackTrace()
                }
            }
        }

    }
}