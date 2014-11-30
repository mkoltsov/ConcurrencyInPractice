/**
 * Uncompleted
 */
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import java.util.concurrent.atomic.AtomicStampedReference

class LinkedQueue<E> {
    private static class Node<E> {
        final E item
        final AtomicReference<E> next

        Node(item, Node<E> next) {
            this.item = item
            this.next = new AtomicReference<Node<E>>(next)
        }
    }

    private final Node<E> dummy = new Node<E>(null, null)
    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy)
    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy)

    def put(E item) {
        Node<E> newNode = new Node<E>(item, null)
        while (true) {
            Node<E> curTail = tail.get()
            Node<E> tailNext = curTail.next.get()
            if (curTail == tail.get()) {
                if (tailNext == null) {
                    tail.compareAndSet(curTail, tailNext)
                } else {
                    if (curTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(curTail, newNode)
                        return true
                    }
                }
            }
        }
    }
}

def queue = new LinkedQueue<Integer>()
AtomicReferenceFieldUpdater<Integer, Integer> nextUpdater = AtomicReferenceFieldUpdater.newUpdater(Integer, Integer, "next")
//AtomicStampedReference<Integer> mark = new AtomicStampedReference<>()
Integer start = Integer.valueOf(0)
Integer.compare()
def task = new Runnable() {
    @Override
    void run() {
        def rnd = new Random().nextInt(10)
        println "i'm pushing $rnd"
        println queue.put(rnd)
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
