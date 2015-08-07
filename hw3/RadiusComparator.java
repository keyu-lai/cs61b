import java.util.Comparator;

/**
 * MassComparator.java
 */

public class RadiusComparator implements Comparator<Planet> {

    public RadiusComparator() {
    }

    /** Returns the difference in mass as an int.
     *  Round after calculating the difference. */
    public int compare(Planet planet1, Planet planet2) {
    	double tmp = planet1.getRadius() - planet2.getRadius();
        if(tmp > 0)
        	return 1;
        return (tmp == 0)? 0: -1;
    }
}