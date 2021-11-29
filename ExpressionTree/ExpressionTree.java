/**
 * ExpressionTree takes a postfix expression and converts it into an Expression Tree. Traversal methods can be called onto the tree.
 *
 * @author Kevin Song
 * @version 10/23/21
 */
public class ExpressionTree
{
    /** Node for a binary tree **/
    public class Node {
        public Object element;
	public Node left;
	public Node right;
	public Node (Object o){
    	    this (o, null, null);
	}
	public Node (Object o, Node l, Node r) {
		element = o;
		left = l;
		right = r;
	}
	
	public String toString() {
		return element.toString();
	}
    }
    
    
    private Node root; // This expression tree will be used for the prefix, infix, and postfix methods
    private String postfix = ""; // This postfix expression is the input passed in by the user, not the tree traversal method
    public ExpressionTree(String postfix)
    {
        this.postfix = postfix;
    }
    /**
     * Converts a postfix expression into a corresponding Expression Tree.
     */
    public void convert()
    {
        ArrayStack<Node> stack = new ArrayStack<Node>();
        for (int i = 0; i < postfix.length(); i += 1) // Walk through every character of the postfix expression
        {
            char c = postfix.charAt(i);
            if (Character.isDigit(c)) // Code is from parserhelper: finds any digit numbers
            {
                String number = postfix.charAt(i) + "";
                for (int j = i + 1; j < postfix.length(); ++j) 
                {
                    if (Character.isDigit(postfix.charAt(j))) 
                    {
                        number += postfix.charAt(j);
                        i = j;
                    } 
                    else 
                    {
                        break;
                    }
                }
                Node a = new Node(number);
                stack.push(a); // Pushes the node onto the stack
            }
            else if(c != ' ') // ignores spaces
            {
                String convertedOperator = postfix.substring(i, i+1);
                
                Node a1 = new Node(stack.top(), stack.top().left, stack.top().right); // Constructs a new node from the top expression tree. If the top is
                 // only a number node, then the second and third parameters will default to null. This avoids unintentionally setting children to null.
                stack.pop(); 
                
                Node a2 = new Node(stack.top(), stack.top().left, stack.top().right);
                stack.pop();
                
                Node b = new Node(convertedOperator, a2, a1); // The hw algorithm seems to put the first pop as the right child and second pop as the left
                // child according to how the outputs look like
                stack.push(b); // Pushes the expression tree onto the stack
            }
        }
        root = new Node(stack.top(), stack.top().left, stack.top().right);
        stack.pop(); // empties the stack
    }
    
    public void prefix()  //used as a driver for real prefix method
    {
        System.out.print("Prefix: ");
        prefix(root);
        System.out.println();
    }
    /**
     * Prints the expression tree using prefix traversal
     * @param Node   the expression tree
     */
    private void prefix(Node t)
    {
        if(t != null)
        {
            System.out.print(t + " ");
            prefix(t.left);
            prefix(t.right);
        }
    }
    
    public void infix()  //used as a driver for real prefix method
    {
        System.out.print("Infix: ");
        infix(root);
        System.out.println();
    }
    /**
     * Prints the expression tree using infix traversal
     * @param Node   the expression tree
     */
    private void infix(Node t)  
    {
	if(t.left != null)  //assume all operators are binary operands
	{
            System.out.print("("); 
            infix(t.left);
	}
	System.out.print(t );
	if(t.left != null)  //assume all operators are binary operands
	{
	     infix(t.right);
	     System.out.print(")");
        }
    }
    
    public void postfix()  //used as a driver for real postfix method
    {
        System.out.print("Postfix: ");
        postfix(root);
        System.out.println();
    }
    /**
     * Prints the expression tree using postfix traversal
     * @param Node   the expression tree
     */
    private void postfix(Node t)
    {
        if(t != null)
        {
            postfix(t.left);
            postfix(t.right);
            System.out.print(t + " ");
        }
    }       
}

