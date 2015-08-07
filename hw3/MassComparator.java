import java.util.Comparator;

/**
 * MassComparator.java
 */

public class MassComparator implements Comparator<Planet> {

    public MassComparator() {
    }

    /** Returns the difference in mass as an int.
     *  Round after calculating the difference. */
    public int compare(Planet planet1, Planet planet2) {
    	double tmp = planet1.getMass() - planet2.getMass();
        if(tmp > 0)
        	return 1;
        return (tmp == 0)? 0: -1;
    }
}