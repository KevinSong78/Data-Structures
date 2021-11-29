
public class Midterm
{
    /**
     * "I understand the ground rules and agree to abide by them. I will not share code or
        assist another student during this exam, nor will I seek assistance from another person or attempt to view their code."
     * 
     * 1A. public int countEvens()
          {
              int evens = 0;
              Node walk = head;
              int element = 0;
              while (walk!= null)
              {
                  element = (Integer)walk.getElement();
                  if(element % 2 == 0)
                  {
                      evens++;
                  }
                  walk = walk.getNext();
                }
              return evens;
          }  
     * 1B. O(n), as the method has to traverse the entire list. 
     * 
     * 2A. Create an ArrayStack<Integer>, then walk through the array and push every index onto the stack. After, pop every element off the stack and
     *  the resulting array will be reversed due to the first in last out property.
     *  
     *  public static void reverseArray(Integer[] array)
        {
            ArrayStack<Integer> stack = new ArrayStack<Integer>();
            int counter = 0;
            for(int i = 0; i < array.length; i++)
            {
                stack.push(array[i]);
            }
            for(int k = stack.size(); k > 0; k --)
            {
                array[counter] = stack.top();
                stack.pop();
                counter ++; 
            }
        }
     *
     * 2B. O(n) because it just needs to walk through the length of the passed in array twice. However, leading constants don't matter. 
     * 
     * 3a. top -> 78 13 55 66 11 88 1 78
     * 3b. front -> 13 55 66 11 88 1 78 13 <- back
     * 3c. O(log(n)) because half the elements are eliminated each at each step
     * 
     * 4. The algorithm I use will be to find the largest element on the left children of the node to be removed. If there are no left children, 
     * the smallest leaf on the right will be used.   
     * 
     * Original tree
     *                8

                  /        \

                6           12

              /   \       /    \

             2     7    10      20

               \       /  \

                 3    9    11

     *  Step 1: Remove 6
     *                8

                  /        \

                3           12

              /   \        /    \

             2      7    10      20

                        /  \

                      9    11

     * Step 2: Remove 10
     *               8

                  /        \

                3           12

              /   \        /    \

             2      7    9       20

                           \
 
                            11
     * Step 3: Remove 8
     *                7

                  /        \

                3           12

              /            /    \

             2           9       20

                           \
 
                            11
     * 
     * 
     * 5. Original Tree
     * 
                       8

                  /        \

                6            12

              /            /    \

             2           10      20

               \        /  \

                 3     9    11
     *
     * Step 1: Insert 7
     * 
                       8

                  /        \

                6            12

              /    \        /    \

             2      7     10      20

               \         /  \

                 3      9    11
     * 
     * Step 2: Insert 1
     *                8

                  /        \

                6            12

              /    \        /    \

             2      7     10      20

           /    \        /  \

          1       3     9    11
     * 
     * Step 3: Insert 14
     *                8

                  /        \

                6             12

              /    \        /     \

             2      7     10       20

           /    \        /  \     /

          1       3     9    11  14 
     * 
     * 6A. Original Tree
     *                8

                  /        \

                9            12

              /            /    \

             2           10      20

               \           \

                 3          11
     * 
     * Preorder: 8 9 2 3 12 10 11 20
     * Inorder: 2 3 9 8 11 10 12 20
     * Postorder: 3 2 9 11 10 20 12 8
     * 
     * 6B. No, because 9 is greater than 8
     */  
}
