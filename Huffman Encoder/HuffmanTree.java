
/**
 * Constructs a HuffmanTree where all leaves are single letters and number of 
 * edges and their direction (left/right) determine the binary encoding.
 *
 * @author Kevin Song
 * @version 11/06/2021
 */
public class HuffmanTree
{
    HuffmanNode root;
    public HuffmanTree()
    {
        // empty constructor for initializing purposes in the Main method
    }
    public HuffmanTree(HuffmanNode huff)
    {
        this.root = huff;
    }
    public void printLegend() // driver method for actual printLegend method
    {
        System.out.println("\nThe binary encodings: " );
        printLegend(root, "");
    }
    /**
     * Prints the binary encodings
     * @param t each HuffmanNode in the tree
     * @param encoding the binary encoding corresponding to the number of edges traversed
     */
    private void printLegend(HuffmanNode t, String encoding)
    {
        if(t.letter.length() > 1)
        {
            printLegend(t.left, encoding + "0");
            printLegend(t.right, encoding + "1");
        }
        else
        {
            System.out.println(t.letter + "=" + encoding);
        }
    }
    /**
     * Converts user input into a BinaryHeap of type HuffmanNodes
     * @param legend user input
     * @return Corresponding BinaryHeap 
     */
    public static BinaryHeap legendToHeap(String legend)
    {
        BinaryHeap heap; // Returned binary heap
        HuffmanNode[] nodes = new HuffmanNode[100]; // Arbitrary size of array to contain user inputs
        int counter = 0; // Counts the number of elements (HuffmanNodes), used to create a specific array size for the binary heap.
        for(int i = 0; i<legend.length(); i++)
        {
            char c = legend.charAt(i);
            if(Character.isLetter(c))
            {
                String letter = legend.substring(i, i+1);
                String number = "";
                for (int j = i+2; j < legend.length() ;j++) // index of frequency
                {
                    char v = legend.charAt(j); // finds first digit
                    if(v != ' ')
                    {
                        number += legend.charAt(j); // concatenates additional digits
                        i = j; // updates i to skip to the next letter
                    }
                    else
                    {
                        break;
                    }
                }
                Double frequency = Double.parseDouble(number); // casts String frequency to a Double
                HuffmanNode a = new HuffmanNode(letter, frequency); 
                nodes[counter] = a; // use counter since i is updating faster than +1
                counter ++;
            }
        }
        HuffmanNode[] preciseNodes = new HuffmanNode[counter]; // Utilizing counter, we can pass a precise array with the exact length equivalent to number of elements
        for(int i = 0; i < counter; i++)
        {
             preciseNodes[i] = nodes[i];
        }
        heap = new BinaryHeap(preciseNodes); // We want a precise array size to avoid nullPointerExceptions in BinaryHeap methods. There are other ways to circumvent this error probably.
        return heap;
    }
    /**
     * Creates the HuffmanTree from the BinaryHeap
     * @param b BinaryHeap
     * @return Corresponding HuffmanTree
     */
    public static HuffmanTree createFromHeap(BinaryHeap b)
    {
        HuffmanNode root = new HuffmanNode(); // Declared outside in order for modifiedTree to declare and compile successfully
        for(int i = 0; b.getSize() != 1 ; i ++)
        {
            HuffmanNode smallestLeft = new HuffmanNode("A", 1.0);
            smallestLeft = (HuffmanNode)(b.deleteMin());
            
            HuffmanNode smallestRight = new HuffmanNode("A", 1.0);
            smallestRight = (HuffmanNode)(b.deleteMin());
            
            HuffmanNode combinedNode = new HuffmanNode(smallestLeft, smallestRight);
            b.insert(combinedNode);
            
            root = combinedNode;
        }
        HuffmanTree modifiedTree = new HuffmanTree(root); 
        return modifiedTree;
    }
    
    // Main method has been moved to an extended Main class
}
