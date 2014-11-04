public final class ThreadSafeList {
    private final ArrayList<Integer> notSafe = [1, 2, 3, 4, 5]

    public synchronized isItIn(int val) {
        notSafe.contains(val)
    }

    public synchronized add(int val){
        notSafe.add(val)
    }
}

def ab = new Runnable() {
    ThreadSafeList threadSafeSet = new ThreadSafeList()

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