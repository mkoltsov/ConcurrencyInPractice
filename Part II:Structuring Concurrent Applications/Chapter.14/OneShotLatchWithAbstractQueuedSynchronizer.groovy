import java.util.concurrent.locks.AbstractQueuedLongSynchronizer

class OneShotLatch {
    private final Sync sync = new Sync()

    public void signal() {sync.releaseShared(0)}


    private class Sync extends AbstractQueuedLongSynchronizer {
        protected int tryAcquireShared(int ignored) {
//            succeed is latch is open(state == 1), else fail
            return (getState() == 1) ? 1 : -1
        }

        protected boolean tryReleaseShared(int ignored) {
            setState(1)
            return true
        }
    }
}