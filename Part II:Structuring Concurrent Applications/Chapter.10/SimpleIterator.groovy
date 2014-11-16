class FastSafeIterator implements Iterator {
    def snapshot = ["chef", "pupa", "diop"]
    def int cursor = 0

    @Override
    boolean hasNext() {
        return cursor < snapshot.size()
    }

    @Override
    String next() {
        if (!hasNext()) {
            throw new NoSuchElementException()
        }
        return snapshot[cursor++]
    }

    @Override
    void remove() {
        snapshot[cursor] = null
    }
}

class Coll implements Iterable {
    @Override
    Iterator iterator() {
        return new FastSafeIterator()
    }
}

def col = new Coll()
col.each { _ -> println _ }