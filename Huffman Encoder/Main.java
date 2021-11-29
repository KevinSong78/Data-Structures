import java.util.Scanner;

/**
 * Main class to run the program
 *
 * @author Kevin Song
 * @version 11/06/2021
 */
public class Main extends HuffmanTree
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Type your legend (or end to stop):\n");
        String infix = s.nextLine();
        s.close();
        
        BinaryHeap bheap = new BinaryHeap();
        bheap = legendToHeap(infix);
        bheap.printHeap();
        
        HuffmanTree htree = new HuffmanTree();
        htree = createFromHeap(bheap);
        htree.printLegend();
    }
}
