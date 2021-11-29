    import java.util.*;
/**
 * This is where infix expressions are converted to postfix.
 */
public class Converter
{
  public List<String> parse(String input) 
  {
    List<String> parsed = new ArrayList<String>();
    for (int i = 0; i < input.length(); ++i) 
    {
        char c = input.charAt(i);
        if (Character.isDigit(c)) 
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
            parsed.add(number);
        }
        else if (c == '*' || c == '/' || 
                c == '+' || c == '^' || 
                c == '-' || c == '(' || c == ')') 
        {
            parsed.add(c + "");
        }
    }
    return parsed;
  }
  
  public String toPostFix(List<String> input)
  {
    String output = "";
    ArrayStack stack = new ArrayStack(); // The ArrayStack
    for(int i = 0; i < input.size(); i++) // input is the returned parsed from parse method
    {
        if(input.get(i).charAt(0) == '*' || input.get(i).charAt(0) == '/' || 
        input.get(i).charAt(0) == '+' || input.get(i).charAt(0) == '^' || 
        input.get(i).charAt(0) == '-' || input.get(i).charAt(0) == '(' || 
        input.get(i).charAt(0) == ')'                        ) 
        {
            stack.push(input.get(i)); // Avoids null pointer exception errors
            // However, immediately pushing operators means they must be popped off if any of the special cases occur and then re-pushed.
            if(input.get(i).charAt(0) == ')') // Special case: end parentheses
            {
                stack.pop(); // Removes ')' immediately
                while(stack.top().equals("(") == false)
                {
                   output = output.concat(stack.top() + " ");
                   stack.pop();
                }
                stack.pop(); // Removes '('
            }  
            else if(input.get(i).charAt(0) == '+' || input.get(i).charAt(0) == '-') 
            // Special case: precedence of addition and subtraction
            {
                stack.pop(); // Removes '*' or '/' so then top() can peek at the current top of the stack
                if(stack.top() != null) // Avoids null pointer exception errors
                {
                    while(stack.top().equals("*")|| stack.top().equals("/") || stack.top().equals("^") || stack.top().equals("+") || stack.top().equals("-"))
                    {
                        output = output.concat(stack.top() + " ");
                        stack.pop();
                        if(stack.top() == null) // Avoids null pointer exception errors
                        {
                           break;
                        }
                    } 
                    stack.push(input.get(i)); // Pushes '*' or '/' back on
                }
                else // If stack is empty, then just push the operator
                {
                    stack.push(input.get(i)); 
                }
            }
            else if(input.get(i).charAt(0) == '*' || input.get(i).charAt(0) == '/') 
            // Special case: precedence of multiplication and division
            {
                stack.pop(); // Removes '^' immediately, so then top() can peek at the current top of the stack
                if(stack.top() != null) // Avoids null pointer exception errors
                {
                    while(stack.top().equals("^") ||stack.top().equals("*")|| stack.top().equals("/"))
                    {
                        output = output.concat(stack.top() + " ");
                        stack.pop();
                        if(stack.top() == null) // Avoids null pointer exception errors
                        {
                            break;
                        }
                    }
                    stack.push(input.get(i)); // Pushes '^' back on
                }
                else // If stack is empty, then just push the operator
                {
                    stack.push(input.get(i));
                }
            }
            else if(input.get(i).charAt(0) == '^') 
            // Special case: precedence of multiplication and division
            {
                stack.pop(); // Removes '^' immediately, so then top() can peek at the current top of the stack
                if(stack.top() != null) // Avoids null pointer exception errors
                {
                    while(stack.top().equals("^") )
                    {
                        output = output.concat(stack.top() + " ");
                        stack.pop();
                        if(stack.top() == null) // Avoids null pointer exception errors
                        {
                            break;
                        }
                    }
                    stack.push(input.get(i)); // Pushes '^' back on
                }
                else // If stack is empty, then just push the operator
                {
                    stack.push(input.get(i));
                }
            }
        }
        else if(true) // Any numbers are concatenated to the output
        {
            output = output.concat(input.get(i) + " ");
        }
    }
    for (int k = stack.size(); k >0; k --) // Clears the stack and concatenates to output
    {
        output = output.concat(stack.top() + " ");
        stack.pop();
    }
    return output;
  }
}
