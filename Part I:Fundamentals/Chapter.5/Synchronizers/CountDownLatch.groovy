def nThreads = 4
final java.util.concurrent.CountDownLatch startGate = new java.util.concurrent.CountDownLatch(1)
final java.util.concurrent.CountDownLatch endGate = new java.util.concurrent.CountDownLatch(nThreads)
(1..nThreads).each {
    new Thread() {
        @Override
        void run() {
            try {
                startGate.await()
                try {
                    new Thread() {
                        @Override
                        void run() {
                            println currentThread().getName() + " has started"
                            sleep 500
                        }
                    }.start()
                }
                finally {
                    endGate.countDown()
                }
            }
            catch (InterruptedException ex) {

            }
        }
    }.start()
}

def start = System.currentTimeMillis()

startGate.countDown()
endGate.await()
println System.currentTimeMillis() - start