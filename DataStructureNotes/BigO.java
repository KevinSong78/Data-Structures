
/**
 * Write a description of class BigO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BigO
{
    /**
     * T(n) = O(f(n)) if and only if there are constants c0 and n0
     * such that T(n) <= c0f(n) for all n>=n0
     * 
     * Big O defines the upper bound of the running time of an operation
     * without regard to constants or machine/compiler factors
     * 
     * O(1): constant
     * O(log n): logarithmic
     * O(n): linear
     * O(2^n): exponential
     * 
     * 
     * 
     * First idea: use an array
     *     However, arrays must be initialized in the beginning,
     *     otherwise it is difficult to expand it. Additionally,
     *     insertions and deletions are expensive because some elements
     *     may need to be moved.
     *     
     * Better idea: linked list
     *     Basic idea is to create a node for each element. The node
     *     will contain the element and a pointer to the next node.
     *     This means when we insert and delete nodes, all we need 
     *     to do is move the pointers away.
     *     
     *     
     *     
     */
}
