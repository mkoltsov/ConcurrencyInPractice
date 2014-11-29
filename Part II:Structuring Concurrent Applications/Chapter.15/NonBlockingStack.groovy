import java.util.concurrent.atomic.AtomicReference

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
        def final E iten
        def Node<E> next

        public Node(E item) {
            this.iten = item
        }

    }

}
