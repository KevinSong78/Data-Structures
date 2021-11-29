import java.util.*;
/**
 * Write a description of class Trees here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trees
{
    /**
     * Tree defined recursively
     * A tree is a collection of nodes. The collection can be empty;
     * otherwise, a tree consists of a distinguished node r, called the root,
     * and zero or more non-empty sub trees each of whose roots are connected
     * by a directed edge from r.
     * 
     * A tree is a collection of N nodes, one of which is the root and N-1 edges
     * 
     * The root of each subtree is said to be a child of r and r is the parent.
     * Leaves: nodes with no children (external nodes)
     * Internal nodes: nodes with children
     * Siblings: nodes with the same parent
     * 
     * A path from node n1 to nk is defined as a sequence of nodes, meaning
     * the path goes through the parents of each node until it reaches k.
     * The length of this path is the number of edge on the path: k-1
     * Length of a path to itself is 0.
     * There is exactly one path from the root to each node in a tree.
     * 
     * The height of a node is the amount of edges it has to the bottom.
     * The height of a tree is the amount of edges below the root (x-1 nodes)
     * 
     * A binary tree is a tree which no node can have more ethan 2 children
     * 
     * Preorder traversal: the root is visited first
     */
    /** 
     public void preorderPrint(BinaryTree bt) // this is technically a
    // tree and a node as the param
    {
        if(bt == null) return;
        System.out.println(bt.value);
        preorderPrint(bt.leftChild);
        preorderPrint(bt.rightChild);
    }
    */ 
}
