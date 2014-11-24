//#TODO use sane hashing algorithmx§
import groovy.transform.ToString

@ToString
class StripedSet{
    def final int N_LOCKS = 16
    def final String[] buckets
    def final Object[] locks

    StripedSet(int numBuckets){
        buckets = new String[numBuckets]
        locks = new Object[numBuckets]
        (0..numBuckets-1).each {locks[it] = new Object()}
    }

    private final int hash(Object key){
        return Math.abs(key.hashCode() % buckets.length)
    }

    public Object get(String key){
        def hash = hash(key)

        synchronized (locks[hash % N_LOCKS]){
            if (buckets[hash].equals(key)){
                return buckets[hash]
            }
        }
        return null
    }

    public void clear(){
        buckets.eachWithIndex {obj, cnt ->
            synchronized (locks[cnt % N_LOCKS]){
                buckets[cnt] = null
            }
        }
    }

    public boolean add(String key){
        def hash = hash(key)
        synchronized (locks[hash % N_LOCKS]){
             def f = buckets[hash]
            println "$key;$f;$hash"
            if (!key.equals(buckets[hash])){
                buckets[hash] = key
                return true
            }
        }
        return false
    }
}

def set = new StripedSet(16)

set.add("chef")
set.add("pupa")
set.add("buba")
set.add("diop")

println set.get("chef")

//println set