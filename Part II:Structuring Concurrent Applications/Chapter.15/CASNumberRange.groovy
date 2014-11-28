import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicReference

class CasNumberRange1 {
    private static class IntPair {
        def lower
        def upper
    }
    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(lower: 0, upper: 0))

    def getLower() {
        values.get().lower
    }

    def getUpper() {
        values.get().upper
    }

    def void setLower(int i){
    while (true){
        IntPair oldV = values.get()

        if(i>oldV.upper){
            println "can't set"
            throw new IllegalArgumentException("can't set lower more than upper")
        }
        IntPair newV = new IntPair(lower:  i,upper:  oldV.upper)
        if (values.compareAndSet(oldV, newV)){
            println "$i is the new lower"
            return
        }
    }
    }
}

def range = new CasNumberRange1()

def task10 = new Runnable() {
    @Override
    void run() {
        range.setLower(-1*new Random().nextInt(10))
    }
}

Executors.newCachedThreadPool().with {
    execute(task10)
    execute(task10)
    execute(task10)
    execute(task10)
    execute(task10)
    execute(task10)
    execute(task10)
}
