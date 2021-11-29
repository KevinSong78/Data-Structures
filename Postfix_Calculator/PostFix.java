import java.util.Scanner;
import java.util.*;
/**
 * This is a calculator that performs an infix to postfix conversion
 * and then evaluates the resulting postfix expression.
 * 
 * @author Kevin Song
 * @version 10/12/2021
 */
public class PostFix 
{
    private String postFix; // This is the postfix expression that will be output to the user
    public PostFix(String input)
    {
        postFix = input;
    }
    public static void main(String[] args)
    {
        Converter expression = new Converter();
        
        Scanner s = new Scanner(System.in);
        System.out.println("Type your infix expression:");
        String infix = "";
        infix += s.nextLine();
        s.close();
        
        String output = expression.toPostFix(expression.parse(infix));
        System.out.println("Converted to postfix: " + output);
        
        PostFix postFixOutput = new PostFix(output);
        System.out.println("Answer is: " + postFixOutput.calculator(output));
    }
    /**
     * This method will push all the numbers in the postfix expression onto a stack, where then 2 will be popped
     * off at a time whenever an operator is found. The result will be pushed back onto the stack and repeat for every operator. The final number
     * in the stack is the evaluation.
     * @param input     The postfix expression to evaluate
     */
    private double calculator(String input) 
    { 
        double answer = 0; // the evaluation that will be returned
        double operand1, operand2; // the 2 operands that will be calculated for any operator and added to answer
        ArrayStack<Double> stack = new ArrayStack<Double>();
        for (int i = 0; i < input.length(); i ++) // Walk through every character of the postfix expression
        {
            char c = input.charAt(i);
            if (Character.isDigit(c)) // Code is from parserhelper: finds numbers
            {
                String number = input.charAt(i) + "";
                for (int j = i + 1; j < input.length(); ++j) 
                {
                    if (Character.isDigit(input.charAt(j))) 
                    {
                        number += input.charAt(j);
                        i = j;
                    } 
                    else 
                    {
                        break;
                    }
                }
                double convertedNumber = Double.parseDouble(number); 
                stack.push(convertedNumber); // Pushes the number onto the stack
            }
            else if (input.charAt(i) == '*' || input.charAt(i) == '/' || 
                    input.charAt(i) == '+' || input.charAt(i) == '^' || 
                    input.charAt(i) == '-' || input.charAt(i) == '(' || 
                    input.charAt(i) == ')'                        )  // Finds operators
            {
                if (input.charAt(i) == '*')
                {
                    operand1 = stack.pop(); // the first operand is the top number
                    operand2 = stack.pop(); // the second operand is the number now on top after the first pop
                    answer = operand1 * operand2; // Note: operand1 and operand2 order may need to switch depending on the operator
                    stack.push(answer); // Push back the result so it can be utilized as a new operand
                }
                else if (input.charAt(i) == '+')
                {
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    answer = operand1 + operand2;
                    stack.push(answer);
                }
                else if (input.charAt(i) == '-')
                {
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    answer = operand2 - operand1;
                    stack.push(answer);
                }
                else if (input.charAt(i) == '/')
                {
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    answer = operand2 / operand1;
                    stack.push(answer);
                }
                else if (input.charAt(i) == '^')
                {
                    operand1 = stack.pop();
                    operand2 = stack.pop();
                    answer = Math.pow(operand1,operand2);
                    stack.push(answer);
                }
            }
            else // this means a space, so nothing happens
            {
            }
        }
        return answer;
    }
}
