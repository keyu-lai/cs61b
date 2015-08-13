package ngordnet;
import ngordnet.YearlyRecordProcessor;

public class WordLengthProcessor implements YearlyRecordProcessor {
    public double process(YearlyRecord yearlyRecord) {
    	long num = 0, den = 0;
    	for(String s: yearlyRecord.words()) {
    		num += s.length() * yearlyRecord.count(s);
    		den += yearlyRecord.count(s);
    	} 
    	return (double)num / (double)den;
    }
}