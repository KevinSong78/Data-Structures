import java.util.Scanner;
/**
 * Main class to run the program
 *
 * @author Kevin Song
 * @version 10/23/2021
 */
public class Main
{
    public static void main(String[] args)
    {
        while(true)
        {
            Scanner s = new Scanner(System.in);
            System.out.println("Type your expression (or end to stop):\n");
            String infix = s.nextLine();
            if(infix.equals("end"))
            {
                s.close();
                break;
            }
            
            Converter expression = new Converter();
            String output = expression.toPostFix(expression.parse(infix));
        
            ExpressionTree tree = new ExpressionTree(output);
            tree.convert();
            tree.prefix();
            tree.infix();
            tree.postfix();
            
            s.close();
        }
    }
}
