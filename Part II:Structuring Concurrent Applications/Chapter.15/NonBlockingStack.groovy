import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicReference

/**
 * Treiber's algorithm'86
 * @param < E >
 */
class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<>()

    def void push(E item) {
        Node<E> newHead = new Node<>(item)
        Node<E> oldHead

        def clos = {
            oldHead = top.get()
            newHead.next = oldHead
        }

        clos()
        while (!top.compareAndSet(oldHead, newHead)) {
            clos()
        }
    }

    def E pop() {
        Node<E> newHead
        Node<E> oldHead

        def clos = {
            oldHead = top.get()
            if (oldHead == null) {
                return null
            }
            newHead = oldHead.next
            newHead.next = oldHead
        }
        clos()
        while (!top.compareAndSet(oldHead, newHead)) {
            clos()
        }
        return oldHead.item
    }

    private static class Node<E> {
        def final E item
        def Node<E> next

        public Node(E item) {
            this.item = item
        }

    }

}

def stack = new ConcurrentStack<Integer>()
def task = new Runnable() {
    @Override
    void run() {
        def rnd = new Random().nextInt(10)
        println "i'm pushing $rnd"
        stack.push(rnd)
        println stack.pop()
    }
}

Executors.newCachedThreadPool().with {
    execute(task)
    execute(task)
    execute(task)
    execute(task)
    execute(task)
    execute(task)
    execute(task)
    execute(task)
    execute(task)
    execute(task)
}