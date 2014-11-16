import java.util.concurrent.Executors

class LockOrdering {
    def tieLock = new Object()

    def void transferMoney(final Expando fromAcct, final Expando toAcct, final int amount) {

        def transfer = {
            if (fromAcct.getBalance() < amount) {
                println "insufficient funds"
            } else {
                fromAcct.debit(amount)
                toAcct.credit(amount)
            }
        }

        fromAcct.getBalance = {
            return new Random().nextInt(10)
        }

        fromAcct.debit = { sum ->
            println "deposited $sum"
        }

        toAcct.credit = { sum ->
            println "withdrawn $sum"
        }
        int fromHash = System.identityHashCode(fromAcct)
        int toHash = System.identityHashCode(toAcct)

        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    transfer()
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    transfer()
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        transfer()
                    }
                }
            }
        }
    }
}

def lockOrdering = new LockOrdering()
def client = new Runnable() {
    @Override
    void run() {
        println Thread.currentThread().getName() + "has started"
        lockOrdering.transferMoney(new Expando(), new Expando(), new Random().nextInt(10))
    }
}
Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).with {
    execute(client)
    execute(client)
    execute(client)
    execute(client)
}