import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        // tester = new StaffCalculator(); // Comment me out to test your Calculator
        tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS
    // YOUR CODE HERE
    @Test 
    public void testAddition() {
        int res;
        res = tester.add(1503, 1230);
        assertEquals(2733, res);
        res = tester.add(-1503, 1230);
        assertEquals(-273, res);
        res = tester.add(1503, -1230);
        assertEquals(273, res);
    }

    @Test 
    public void testMultiplation() {
        int res;
        res = tester.multiply(153, 123);
        assertEquals(18819, res);
        res = tester.multiply(-153, 123);
        assertEquals(-18819, res);
        res = tester.multiply(153, -123);
        assertEquals(-18819, res);
    }

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}