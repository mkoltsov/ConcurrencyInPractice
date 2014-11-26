import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantReadWriteLock

class ReadWriteMap<K, V> {
    private final Map<K, V> map = new HashMap<K, V>()

    private final lock = new ReentrantReadWriteLock()

    private final r = lock.readLock()
    private final w = lock.writeLock()

    public V put(K key, V value) {
        w.lock()
        try {
            return map.put(key, value)
        }
        finally {
            w.unlock()
        }
    }

    public V get(K key) {
        r.lock()
        try {
            return map.get(key)
        }
        finally {
            r.unlock()
        }
    }
}

def map = new ReadWriteMap<Integer, String>()

def task8 = new Runnable() {
    @Override
    void run() {
        def randomness = new Random().nextInt(5)
        map.put(randomness, "text" + randomness)
        println map.get(new Random().nextInt(5))
    }
}
Executors.newCachedThreadPool().with {
    execute(task8)
    execute(task8)
    execute(task8)
    execute(task8)
    execute(task8)
    execute(task8)
    execute(task8)
    execute(task8)
}