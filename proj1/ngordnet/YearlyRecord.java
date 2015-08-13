package ngordnet;

import java.util.TreeSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collection;
import java.util.HashMap;

public class YearlyRecord {
    /** Creates a new empty YearlyRecord. */
    private HashMap<String, Integer> ori;
    private TreeSet<String> treeSet;
    private HashMap<String, Integer> map_rank;
    private Collection<Number> counts;

    public YearlyRecord() {
        ori = new HashMap<String, Integer>();
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        this();
        ori = otherCountMap;
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        if(ori.containsKey(word))
            return ori.get(word);
        else 
            return 0;
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        ori.put(word, count);
    }

    /** Returns the number of words recorded this year. */
    public int size() {
        return ori.size();
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        update();
        return treeSet;
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() { 
        updateCounts();
        return counts;
    }

    /* Returns rank of WORD. Most common word is rank 1. 
     * If two words have the same rank, break ties arbitrarily. 
     * No two words should have the same rank. */
      
    public int rank(String word) {
        updateRank();
        return map_rank.get(word);
    }

    private void update() {
        if(treeSet == null) {
            treeSet = new TreeSet<String>(new ValueComparator());
            for (String s: ori.keySet())
                treeSet.add(s);
        }
    }

    private void updateRank() {
        update();
        if(map_rank == null) {
            map_rank = new HashMap<String, Integer>();
            int count = treeSet.size();
            for(String s: treeSet) 
                map_rank.put(s, count--); 
        }
    }

    private void updateCounts() {
        update();
        if(counts == null) {
            counts = new ArrayList<Number>();
            for(String s: treeSet) 
                counts.add(ori.get(s)); 
        }
    }


    class ValueComparator implements Comparator<String> {

        // Note: this comparator imposes orderings that are inconsistent with equals.   
        // return 0 would merge keys. 
        public int compare(String a, String b) {
            if (ori.get(a) > ori.get(b)) {
                return 1;
            } else {
                return -1;
            } 
        }
    }
} 