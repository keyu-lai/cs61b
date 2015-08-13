package ngordnet;

import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.ArrayList;

public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {    
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {}

    /** Returns the years in which this time series is valid. Doesn't really
      * need to be a NavigableSet. This is a private method and you don't have 
      * to implement it if you don't want to. */
    private NavigableSet<Integer> validYears(int startYear, int endYear) {
      TreeSet<Integer> res = new TreeSet<Integer>();
      for(int y = startYear; y <= endYear; ++y) {
        if(containsKey(y))
          res.add(y);
      }
      return res;
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR. 
     * inclusive of both end points. */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
      for(int y = startYear; y <= endYear; ++y) {
        if(containsKey(y))
          if(ts.containsKey(y))
            put(y, ts.get(y));
      } 
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts) {
      for(Integer i: ts.keySet()) {
        put(i, ts.get(i));
      }
    }

    /** Returns the quotient of this time series divided by the relevant value in ts.
      * If ts is missing a key in this time series, return an IllegalArgumentException. */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
      TimeSeries<Double> res = new TimeSeries<Double>();
      for(Integer i: keySet()) {
        if(ts.containsKey(i)) {
          res.put(i, get(i).doubleValue()/ts.get(i).doubleValue());
        } else {
          throw new IllegalArgumentException();
        }
      }
      return res;
    }

    /** Returns the sum of this time series with the given ts. The result is a 
      * a Double time series (for simplicity). */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
      TimeSeries<Double> res = new TimeSeries<Double>();
      for(Integer i: ts.keySet()) {
        if(containsKey(i)) {
          res.put(i, get(i).doubleValue() + ts.get(i).doubleValue());
        } else {
          res.put(i, ts.get(i).doubleValue());
        }
      }
      return res;     
    }

    /** Returns all years for this time series (in any order). */
    public Collection<Number> years() {
      ArrayList<Number> res = new ArrayList<Number>();
      for(Integer i: keySet()) 
        res.add(i);
      return res;
    }

    /** Returns all data for this time series. 
      * Must be in the same order as years(). */
    public Collection<Number> data() {
      ArrayList<Number> res = new ArrayList<Number>();
      for(Number i: years()) 
        res.add(get(i));
      return res;      
    }
}