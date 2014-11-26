import groovy.transform.Canonical

import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

@Canonical
class Account {
    def lock
    int amount

    def debit(amount) {
        println "debitted $amount"
        this.amount += amount
    }

    def credit(amount) {
        println "credited $amount"
        this.amount -= amount
    }
}

@Canonical
class TryLock {
    def Account fromAcct
    def Account toAcct

    def transferMoney(amount) {
        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.amount < amount) {
                                println "insufficient funds"
                                throw new RuntimeException()
                            } else {
                                fromAcct.credit(amount)
                                toAcct.debit(amount)
                                println "i've transferred $amount to $toAcct"
                            }
                        } finally {
                            toAcct.lock.unlock()
                        }
                    }

                } finally {
                    fromAcct.lock.unlock()
                }
            }
        }

    }
}

def from = new Account(new ReentrantLock(), 100)
def to = new Account(new ReentrantLock(), 10)

Executors.newCachedThreadPool().with {
    execute(new Runnable() {
        @Override
        void run() {
            new TryLock(from, to).transferMoney(200)
        }
    })

    execute(new Runnable() {
        @Override
        void run() {
            new TryLock(from, to).transferMoney(10)
        }
    })
}