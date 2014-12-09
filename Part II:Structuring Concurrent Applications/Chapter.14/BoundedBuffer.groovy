import java.util.concurrent.Executors

abstract class BaseBoundedBuffer<V> {
    private final V[] buffer
    private int tail
    private int head
    private int count

    protected BaseBoundedBuffer(int capacity) {
        this.buffer = (V[]) new Object[capacity]
    }

    protected synchronized void doPut(V elem) {
        buffer[tail] = elem
        if (++tail == buffer.length)
            tail = 0
        count++
    }

    protected synchronized final V doTake() {
        V v = buffer[head]
        buffer[head] = null
        if (++head == buffer.length)
            head = 0
        --count
        return v
    }

    public synchronized boolean isFull() {
        return count == buffer.length
    }

    public synchronized isEmpty() {
        count == 0
    }
}

def buffer = new BaseBoundedBuffer<Integer>(10){}

def task = new Runnable() {
    @Override
    void run() {
        try {while (true){
            def item = buffer.doTake()
            break
        }}
        catch (Exception e){
            sleep(500)
        }
    }
}

Executors.newCachedThreadPool().with {
    execute(task)
    execute(task)
    execute(task)
    execute(task)
    execute(task)
}