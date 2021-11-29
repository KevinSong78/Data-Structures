import javax.swing.JOptionPane;
/**
 * Data Structures with Evan Korth
 * Kevin Song
 */
public class RecursionNotes
{
    /**
     * 9/13/21
     * Recursion: 
     * 
     * Method calls itself. 
    */
    public String subdirectory(String file){ //recieve a directory and
        // list all the file names including those within subdirectories.
        String x = "";
        // subdirectory will read all files in directory and
        // concatenate to x
        
        // then, subdirectory will call subdirectory with parameter
        // of the next subdirectory found while parsing the directory.
        // Each call will return its individual x and cocatenate it 
        // to the super x
        
        // We can also use an array, which is better.
        return x;
    }
    public static void main (String args[]){
        //byte x = 3;
        //count(x);
        //System.out.println();
        System.out.println( 5+ "! = " + findFactorial(5));
        /**
         * 
         long number, fibonacciValue;
        String numberAsString;
        
        numberAsString = JOptionPane.showInputDialog("What fib value?");
        number = Long.parseLong(numberAsString);
        fibonacciValue = fibonacci(number);
        
        System.out.println(fibonacciValue);
        System.exit(0);
        */
    }
    public static int findFactorial(int number)
    {
        if( number <= 1)
        {
            return 1; // base case
        }
        else
        {
            return (number * findFactorial(number - 1)); // making progress
        }
    }
    public static void count (byte index){ //simplest recursion
        System.out.println(index);
        if(index>2){
            count(index+=(1)); //Will end at -128 because bytes
            // only go up to 127 (8 bytes of memory)
        }
    }
    /**
     * When you run a program, the computer creates a stack.
     * This is called the program stack. Each time you invoke
     * a method, the method's activation record is placed 
     * on top of the stack. When the method returns or exits,
     * the method is popped off (removed from) the stack.
     * 
     * There are two types of memory.
     * Heap: dynamic (where data created in the program is stored)
     * Stack: methods (starting with main())
     * 
     * When you call a method, you push the method on the stack.
     * You push the method's activation frame on the stack.
     * Returned values will go to the heap.
     * 
     * Infinite recursion will eventually cause java to throw a 
     * stack overflow error.
     * 
     * Recursion is "expensive". It has multiple activation frames and
     * there is overhead involved with calling a method.
     * The advantage to recursion is ease of writing and understanding.
     */
    public static void upAndDown(int n)
    {
        System.out.print("\nLevel:" + n);
        if (n<4){
            upAndDown(n+1);
        }
        System.out.print("\nLEVEL:" +n);
    }
    
    public static long fibonacci (long n) //terribly inefficient
    //as the amount of calls increases exponentially 
    {
        if(n == 0 || n == 1)
            return n;
        else
            return fibonacci(n-1) + fibonacci(n-2);
    }
    /**
     * 
     */
}

