package ngordnet;

import java.util.HashMap;
import java.util.Collection;
import edu.princeton.cs.introcs.In;

public class NGramMap {
    HashMap<Integer, YearlyRecord> words;
    TimeSeries<Long> counts;

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        words = new HashMap<Integer, YearlyRecord>();
        counts = new TimeSeries<Long>();

        In file = new In(wordsFilename);
        while(file.hasNextLine()) {
            String word = file.readString();
            Integer i = file.readInt();
            if(!words.containsKey(i)) 
                words.put(i, new YearlyRecord());
            words.get(i).put(word, file.readInt());
            file.readInt();
        }

        file = new In(countsFilename);
        while(file.hasNextLine()) {
            String line = file.readLine();
            String[] parts = line.split(",");
            counts.put(Integer.parseInt(parts[0]), Long.parseLong(parts[1]));
        }
    }
    
    /** Returns the absolute count of WORD in the given YEAR. If the word
      * did not appear in the given year, return 0. */
    public int countInYear(String word, int year) {
        if(words.containsKey(year)) 
            return words.get(year).count(word);
        else
            return 0;
    }

    /** Returns a defensive copy of the YearlyRecord of YEAR. */
    public YearlyRecord getRecord(int year) {
        if(words.containsKey(year)) 
            return words.get(year);
        else
            return null;
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return counts;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear) {
        TimeSeries<Integer> res = new TimeSeries<Integer>();
        for(int i = startYear; i < endYear; ++i) {
            if(words.containsKey(i)) {
                int tmp = words.get(i).count(word);
                res.put(i, tmp);
            }
        }
        return res;
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        TimeSeries<Integer> res = new TimeSeries<Integer>();
        for(int i: words.keySet()) {
            if(words.containsKey(i)) {
                int tmp = words.get(i).count(word);
                res.put(i, tmp);
            }           
        }
        return res;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear) {
        return countHistory(word, startYear, endYear).dividedBy(counts);
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        return countHistory(word).dividedBy(counts);
    }

    /** Provides the summed relative frequency of all WORDS between
      * STARTYEAR and ENDYEAR. If a word does not exist, ignore it rather
      * than throwing an exception. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        TimeSeries<Double> res = new TimeSeries<Double>();
        for(String s: words) {
            res = res.plus(weightHistory(s, startYear, endYear));
        }
        return res;
    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> res = new TimeSeries<Double>();
        for(String s: words) {
            res = res.plus(weightHistory(s));
        }
        return res;
    }

    /** Provides processed history of all words between STARTYEAR and ENDYEAR as processed
      * by YRP. */
    public TimeSeries<Double> processedHistory(int startYear, int endYear, YearlyRecordProcessor yrp) {
        TimeSeries<Double> res = new TimeSeries<Double>();
        for(int i = startYear; i <= endYear; ++i) {
            if(words.containsKey(i))
                res.put(i, yrp.process(words.get(i))); 
            else
                res.put(i, 0.0);
        } 
        return res;
    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> res = new TimeSeries<Double>();
        for(Integer i: words.keySet()) {
            if(words.containsKey(i))
                res.put(i, yrp.process(words.get(i))); 
            else
                res.put(i, 0.0);           
        }
        return res;
    }
}


