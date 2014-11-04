public final class ThreadSafeList2 {
    private final Object myLock = new Object()
    private final ArrayList<Integer> notSafe = [1, 2, 3, 4, 5]

    public isItIn(int val) {
        synchronized (myLock) {
            notSafe.contains(val)
        }
    }

    public add(int val) {
        synchronized (myLock) {
            notSafe.add(val)
        }
    }
}

def ab = new Runnable() {
    ThreadSafeList2 threadSafeSet = new ThreadSafeList2()

    @Override
    void run() {
        synchronized (this) {
            def val = new Random().nextInt(10)
            def ts = threadSafeSet.isItIn(val)
            if (ts){
                println "$val is in"
            }
            else {
                println "$val is out"
                threadSafeSet.add(val)
            }
        }
    }
}
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()
new Thread(ab).start()