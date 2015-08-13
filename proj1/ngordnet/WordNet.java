
package ngordnet;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import ngordnet.GraphHelper;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {

  private HashMap<String, ArrayList<Integer>> strInt;
  private ArrayList<String[]> intStr;
  private Digraph g;

  // public static void main(String[] args) {
  //   WordNet word = new WordNet("wordnet/synsets.txt", "wordnet/hyponyms.txt");
  //   System.out.println(word.hyponyms("animal"));
  // }

  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
  public WordNet(String synsetFilename, String hyponymFilename) {
  	readSynset(synsetFilename);
    setHyponym(hyponymFilename);
  }

  private void setHyponym(String hyponymFilename) {
    g = new Digraph(intStr.size());
    In file = new In(hyponymFilename);

    String line = file.readLine();
    while(line != null) {
      String[] parts = line.split(",");
      int c = Integer.parseInt(parts[0]);
      for(int i = 1; i < parts.length; ++i) {
        g.addEdge(c, Integer.parseInt(parts[i]));
      }
      line = file.readLine();
    }
  }

  private void readSynset(String synsetFilename) {
    intStr = new ArrayList<String[]>(); 
    strInt = new HashMap<String, ArrayList<Integer>>();
  	In file = new In(synsetFilename);

    String line = file.readLine();
    int count = 0;
   	while(line != null) {
   		String[] parts = line.split(",");
      parts = parts[1].split(" ");
   		intStr.add(parts);
      for(String s: parts) {
        if(!strInt.containsKey(s))
          strInt.put(s, new ArrayList<Integer>());
        strInt.get(s).add(count);
      }
   		line = file.readLine();
      ++count;
   	}
  }

  /* Returns true if NOUN is a word in some synset. */
  public boolean isNoun(String noun) {
    return strInt.containsKey(noun);
  }

  // /* Returns the set of all nouns. */
  public Set<String> nouns() {
    return strInt.keySet();
  }

  // * Returns the set of all hyponyms of WORD as well as all synonyms of
  //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
  //   * all of these synsets. See http://goo.gl/EGLoys for an example.
  //   * Do not include hyponyms of synonyms.
    
  public Set<String> hyponyms(String word) {
    if(!isNoun(word))
      return null;

    TreeSet<Integer> synonymsNo = new TreeSet<Integer>();
    TreeSet<String> synonyms = new TreeSet<String>();
    for(Integer i: strInt.get(word)) {
      synonymsNo.add(i);
      for(String s: intStr.get(i))
        synonyms.add(s);
    }

    TreeSet<Integer> hyponymsNo = (TreeSet<Integer>)GraphHelper.descendants(g, synonymsNo);
    TreeSet<String> res = new TreeSet<String>();
    for(Integer i: hyponymsNo) {
      for(String s: intStr.get(i)) {
        res.add(s);
      }
    }
    return res;
  }
}


