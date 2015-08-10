import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator;
import java.util.NoSuchElementException;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K, V> implements Map61B<K, V>, Iterable<K>{ 
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;
    private int size = 0;

    @Override
    public V get(K key) { 
        if(front == null)
            return null; 
        else
            return front.get(key).val;
    }

    @Override
    public void put(K key, V val) {
        if(front == null)
            front = new Entry(key, val, null);
        else {
            Entry runner = front;
            while(!runner.key.equals(key) && runner.next != null) 
                runner = runner.next;
            if(runner.key.equals(key))
                return;
            runner.next = new Entry(key, val, null);
        }
        ++size;
    }

    @Override
    public boolean containsKey(K key) {
        Entry runner = front;
        while(runner != null) {
            if(runner.key.equals(key))
                return true;
            runner = runner.next;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        front = null;
        size = 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new ULLMapIter();
    }

    public static <A, B> ULLMap<B, A> invert(ULLMap<A, B> m) {
        ULLMap<B, A> res = new ULLMap<B, A>();
        ULLMap<A, B>.Entry runner = m.front;
        while(runner != null) {
            res.put(runner.val, runner.key);
            runner = runner.next;
        }
        return res;
    }

    private class ULLMapIter implements Iterator<K> {
        private Entry runner = front;
        
        @Override
        public boolean hasNext() {
            if(runner != null) 
                return true;
            else
                return false;
        }

        @Override
        public K next() {
            if(runner == null)
                return null;
            K res = runner.key;
            runner = runner.next;
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k, V v, Entry n) { 
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K k) { 
            if(key.equals(k))
                return this;
            else {
                if(next != null)
                    return next.get(k);
                else
                    return null;
            }
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private K key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private V val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public V remove(K key) { 
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }


}