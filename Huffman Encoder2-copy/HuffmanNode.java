
/**
 * HuffmanNodes are the specific nodes used for the HuffmanTree. We also pass 
 * in an array of HuffmanNodes to BinaryHeap for this assignment
 *
 * @author Kevin Song
 * @version 11/06/2021
 */
public class HuffmanNode implements Comparable
{
    public String letter;
    public Double frequency;
    public HuffmanNode left, right;
    public HuffmanNode() 
    {
        // empty constructor for initializing purposes in createFromHeap()
    }
    public HuffmanNode(String letter, Double frequency)
    {
        this.letter = letter;
        this.frequency = frequency;
    }
    public HuffmanNode(HuffmanNode left, HuffmanNode right)
    {
        this.left = left;
        this.right = right;
        frequency = left.frequency + right.frequency;
        letter = left.letter + right.letter;
    }
    /**
     * Compares the frequency of one HuffmanNode to another
     * @param o object to compare to
     * @return 1 if o is larger, 0 if this is larger
     */
    public int compareTo(Object o)
    {
        HuffmanNode huff;
        huff = (HuffmanNode)(o);
        if(this.frequency < huff.frequency)
        {
            return 1;
        }
        return 0;
    }
    public String toString()
    {
        return "<"+letter + "," + frequency+">";
    }
}
