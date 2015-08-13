/* Starter code for NgordnetUI (part 7 of the project). Rename this file and 
   remove this comment. */

package ngordnet;

import ngordnet.Plotter;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;

/** Provides a simple user interface for exploring WordNet and NGram data.
 *  @author [yournamehere mcjones]
 */
public class NgordnetUI {
    public static void main(String[] args) {
        In in = new In("./ngordnet/ngordnetui.config");

        String wordFile = in.readString();
        String countFile = in.readString();
        String synsetFile = in.readString();
        String hyponymFile = in.readString();
        int startDate = 1505;
        int endDate = 2008;
        // System.out.println("\nBased on ngordnetui.config, using the following: "
        //                    + wordFile + ", " + countFile + ", " + synsetFile +
        //                    ", and " + hyponymFile + ".");
        WordNet wn = new WordNet(synsetFile, hyponymFile);
        NGramMap ngm = new NGramMap(wordFile, countFile);

        while (true) {
              System.out.print("> ");
              String line = StdIn.readLine();
              String[] rawTokens = line.split(" ");
              String command = rawTokens[0];
              String[] tokens = new String[rawTokens.length - 1];
              System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
              switch (command) {
                case "quit": 
                    return;
                case "help":
                    System.out.println("Figure it out yourself!");
                    break;  
                case "range": 
                    startDate = Integer.parseInt(tokens[0]); 
                    endDate = Integer.parseInt(tokens[1]);
                    break;
                case "count":
                    String word = tokens[0];
                    int year = Integer.parseInt(tokens[1]);
                    System.out.println(ngm.countInYear(word, year));
                    break;
                case "hyponyms":
                    String wordh = tokens[0];
                    System.out.println(wn.hyponyms(wordh));
                    break;
                case "history":
                    Plotter.plotAllWords(ngm, tokens, startDate, endDate);
                    break;
                case "hypohist":
                    Plotter.plotCategoryWeights(ngm, wn, tokens, startDate, endDate);
                    break;
                case "wordlength":
                    WordLengthProcessor yrp = new WordLengthProcessor();
                    Plotter.plotProcessedHistory(ngm, startDate, endDate, yrp);
                    break;   
                case "zipf":
                    Plotter.plotZipfsLaw(ngm, Integer.parseInt(tokens[0]));
                    break;                
                default:
                    System.out.println("Invalid command.");  
                    break;
            }
        }
    }
} 
