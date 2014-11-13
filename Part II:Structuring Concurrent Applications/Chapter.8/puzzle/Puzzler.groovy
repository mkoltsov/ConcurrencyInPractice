import groovy.transform.Immutable

import java.util.concurrent.ConcurrentMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService

import java.util.concurrent.atomic.AtomicInteger

public interface Puzzle<P, M> {
    P initialPosition()

    boolean isGoal(P position)

    Set<M> legalMoves(P position)

    P move(P position, M move)
}

@Immutable
class Node<P, M> {
    P pos
    M move
    Node<P, M> prev

    List<M> asMoveList() {
        List<M> solution = new LinkedList<M>();
        for (Node<P, M> n = this; n.move != null; n = n.prev) solution.add(0, n.move); return solution;
    }
}

public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<P>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) { this.puzzle = puzzle; }

    public List<M> solve() {
        P pos = puzzle.initialPosition(); return search(new Node<P, M>(pos: pos, move: null, prev: null));
    }

    private List<M> search(Node<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos); if (puzzle.isGoal(node.pos)) return node.asMoveList();
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);
                Node<P, M> child = new Node<P, M>(pos: pos, move: move, prev: node);
                List<M> result = search(child); if (result != null) return result;
            }
        }
        return null;
    }

}

public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P, Boolean> seen;
    final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();

    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            // block until solution found
            Node<P, M> solnNode = solution.getValue(); return (solnNode == null) ? null : solnNode.asMoveList();
        } finally {
            exec.shutdown();
        }
    }

    protected Runnable newTask(P p, M m, Node<P, M> n) { return new SolverTask(p, m, n); }

    class SolverTask extends Node<P, M> implements Runnable {

        SolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev)
        }

        public void run() {
            if (solution.isSet() || seen.putIfAbsent(pos, true) != null) return;
            // already solved or seen this position
            if (puzzle.isGoal(pos)) solution.setValue(this); else for (M m : puzzle.legalMoves(pos)) exec.execute(newTask(puzzle.move(pos, m), m, this));
        }
    }
}


public class ValueLatch<T> {
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() { return (done.getCount() == 0); }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue; done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await(); synchronized (this) {
            return value;
        }
    }
}
//Solver that Recognizes when No Solution Exists
//public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
//    private final AtomicInteger taskCount = new AtomicInteger(0);
//
//    protected Runnable newTask(P p, M m, Node<P, M> n) { return new CountingSolverTask(p, m, n); }
//
//    class CountingSolverTask extends SolverTask {
//        CountingSolverTask(P pos, M move, Node<P, M> prev) { super(pos, move, prev); taskCount.incrementAndGet(); }
//
//        public void run() {
//            try {
//                super.run();
//            } finally {
//                if (taskCount.decrementAndGet() == 0) solution.setValue(null);
//            }
//        }
//    }
//}