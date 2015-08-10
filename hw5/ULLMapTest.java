import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** ULLMapTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ULLMapTest {
    @Test
    public void testBasic() {
        ULLMap<String, String> um = new ULLMap<String, String>();
        um.put("Gracias", "Dios Basado");
        um.put("Freddies", "San Diego");
        um.put("Hiro", "Los Angle");
        um.put("Hiro", "Duplicate");
        assertEquals(um.get("Hiro"), "Los Angle");
        assertEquals(um.containsKey("Freddies"), true);
        assertEquals(um.size(), 3);
    }

    
    @Test
    public void testIterator() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(1, "two");
        um.put(2, "two");
        Iterator<Integer> umi = um.iterator();
        for(Integer i: um) {
            System.out.println(i+ ": " + um.get(i));
        }
    }

    @Test
    public void testInverse() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        um.put(3, "two");
        um.put(4, "one");
        ULLMap<String, Integer> inv = ULLMap.invert(um); 
        Iterator<Integer> invi = um.iterator();
        for(String i: inv) {
            System.out.println(i + ": " + inv.get(i));
        }
    }    

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ULLMapTest.class);
    }
} 