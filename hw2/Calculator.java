import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList head;
    int size;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public Calculator() {
        head = new EquationList(null, 0, null);
        size = 0;
    }

    public int add(int x, int y) {
        // YOUR CODE HERE
        // int carry = 0, res = 0, a, b;
        // for(int i = 0; i < 32; i++) {
        //     a = (x >> i) & 1;
        //     b = (y >> i) & 1;
        //     res |= ((a ^ b ^ carry) << i);
        //     carry = (a & b) | (a & carry) | (b & carry);       
        // }
        // return res;
        if(y == 0)
            return x;
        return add( x ^ y, (x & y) << 1);
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int res = 0;
        while(y != 0) {
            if((y & 1) == 1) {
                res = add(x, res);
            }
            y >>>= 1;
            x <<= 1;
        }
        return res;       
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        EquationList tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new EquationList(equation, result, null);
        ++size;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        printHistory(size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
        EquationList tmp = head.next;
        for (int i = 0; i < size - n; i++) {
            tmp = tmp.next;
        }
        while(tmp != null) {
            System.out.print(tmp.equation);
            System.out.print(" = ");
            System.out.println(tmp.result);  
            tmp = tmp.next;   
        }     
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList tmp = head;
        if(size > 0) {
            while(tmp.next.next != null) {
                tmp = tmp.next;
            }
            tmp.next = null;
            --size;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        head.next = null;
        size = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        EquationList tmp = head.next;
        while(tmp != null) {
            sum += tmp.result;
            tmp = tmp.next;
        }
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int sum = 1;
        EquationList tmp = head.next;
        while(tmp != null) {
            sum *= tmp.result;
            tmp = tmp.next;
        }
        return sum;
    }
}