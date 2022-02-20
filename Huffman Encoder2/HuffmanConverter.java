import java.io.*;
import java.lang.*;
import java.util.*;

public class HuffmanConverter
{
    // The # of chars in the ASCII table dictates
    // the size of the count[] & code[] arrays.
    public static final int NUMBER_OF_CHARACTERS = 256;
    // the contents of our message...
    private String contents;
    // the tree created from the msg
    private HuffmanTree huffmanTree;
    // tracks how often each character occurs
    private int count[];
    // the huffman code for each character
    private String code[];
    // stores the # of unique chars in contents
    private int uniqueChars = 0; //(optional)
    /** Constructor taking input String to be converted */
    public HuffmanConverter(String input)
    {
        this.contents = input;
        this.count = new int[NUMBER_OF_CHARACTERS];
        this.code = new String[NUMBER_OF_CHARACTERS];
        for(int i = 0; i < count.length; i ++)
        {
            count[i] = 0; // initializing these all to avoid null pointer errors
            code[i] = "";
        }
    }
    
    /**
    * Records the frequencies that each character of our
    * message occurs...
    * I.e., we use 'contents' to fill up the count[] list...
    */
    public void recordFrequencies()
    {
        int counter = 0; // indexes each character
        List<String> listCode = new ArrayList<String>(256); // stores each unique character so to not repeat characters
        for(int i = 0; i < contents.length(); i ++)
        {
            if(listCode.contains(String.valueOf(contents.charAt(i))))
            {
                // This will pass by duplicate characters
            }
            else
            {   // This will add a unique character
                listCode.add(String.valueOf(contents.charAt(i)));
                code[counter] = String.valueOf(contents.charAt(i));
                counter ++;
            }
        }
        for(int i = 0; i < contents.length(); i++){ 
            count[listCode.indexOf(String.valueOf(contents.charAt(i)))] ++; // Will increment the frequency according to the index of the character
        }
    }
    
    /**
    * Converts our frequency list into a Huffman Tree. We do this by
    * taking our count[] list of frequencies, and creating a binary
    * heap in a manner similar to how a heap was made in HuffmanTree's
    * fileToHeap method. Then, we print the heap, and make a call to
    * HuffmanTree.heapToTree() method to get our much desired
    * HuffmanTree object, which we store as huffmanTree.
    */
    public void frequenciesToTree()
    {
        BinaryHeap heap; 
        
        int size = 0; // This will reduce array length from the default 256 to the number of characters in the text file
        for (int i = 0; i < count.length; i++)
        {
            if(count[i] == 0) // recall back to the constructor where all initial values are 0, meaning that if this hits a 0 then we have reached the end.
            {
                size = i+1;
                break;
            }
        }
        HuffmanNode[] preciseNodes = new HuffmanNode[size]; 
        int counter = 0;
        
        for(int i = 0; i < size; i++)
        {
            preciseNodes[i] = new HuffmanNode(code[i], Double.valueOf(count[i]));
        }
        heap = new BinaryHeap(preciseNodes);
        heap.printHeap();
        System.out.println();

        huffmanTree = HuffmanTree.createFromHeap(heap);
        huffmanTree.printLegend();
        System.out.println();
    }
    
    /**
    * Iterates over the huffmanTree to get the code for each letter.
    * The code for letter i gets stored as code[i]... This method
    * behaves similarly to HuffmanTree's printLegend() method...
    * Warning: Don't forget to initialize each code[i] to ""
    * BEFORE calling the recursive version of treeToCode...
    */
    public void treeToCode()
    {
        treeToCode(huffmanTree.root, "");
    }
    
    /**
    * A private method to iterate over a HuffmanNode t using s, which
    * contains what we know of the HuffmanCode up to node t. This is
    * called by treeToCode(), and resembles the recursive printLegend
    * method in the HuffmanTree class. Note that when t is a leaf node,
    * t's letter tells us which index i to access in code[], and tells
    * us what to set code[i] to...
    */
    private void treeToCode(HuffmanNode t, String s)
    {
        if (t.letter.length() > 1){
            treeToCode(t.left, s + "0");
            treeToCode (t.right, s + "1");
        }
        else
        {
            for(int i = 0; i < code.length; i++)
            {
                if (t.letter.equals(code[i])) 
                {
                    code[i + 128] = s; // Adding 128 so as to not override the characters already in code.
                    // This will be useful for encodeMessage() so code can provide the characters and the binary encodings.
                    // It is reasonable to assume a message will not have more than 128 unique characters. 
                }
            }
        }
    }
    
    /**
    * Using the message stored in contents, and the huffman conversions
    * stored in code[], we create the Huffman encoding for our message
    * (a String of 0's and 1's), and return it...
    */
    public String encodeMessage()
    {
        String encodedMessage = ""; 
        System.out.print("Huffman Encoding: \n");
        for(int i = 0; i < contents.length(); i++)
        {
            for(int j = 0; j < code.length; j++)
            {
                if(code[j].equals(String.valueOf(contents.charAt(i))))
                {
                    encodedMessage += code[j+128];
                }
            }
        }
        return encodedMessage;
    }
    
    /**
    * Reads in the contents of the file named filename and returns
    * it as a String. The main method calls this method on args[0]...
    */
    public static String readContents(String filename)
    {
        String message ="";
        File file = new File(filename);
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                message += scan.nextLine() + "\n";
                
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File does not exist.");
            System.exit(0);
        }
        return message;
    }
    
    /**
    * Using the encoded String argument, and the huffman codings,
    * re-create the original message from our
    * huffman encoding and return it...
    */
    public String decodeMessage(String encodedStr)
    {
        String binaryCode = "";
        String decodedMessage = "";
        for(int i = 0; i < encodedStr.length(); i++)
        {
            binaryCode += encodedStr.charAt(i);
            for(int j = 0; j < code.length/2; j++) // divided by 2 as the size of the code array has been halved to fit both the unique characters and their binary encodings
            {
                if(binaryCode.equals(code[j + 128]))
                {
                    decodedMessage += code[j];
                    binaryCode = "";
                    break;
                }
            }
        }
        return decodedMessage;
    }
    
    /**
    * Uses args[0] as the filename, and reads in its contents. Then
    * instantiates a HuffmanConverter object, using its methods to
    * obtain our results and print the necessary output. Finally,
    * decode the message and compare it to the input file.<p>
    * NOTE: Example method provided below...
    */
    public static void main(String args[])
    {
        String input = readContents(args[0]);
        HuffmanConverter object = new HuffmanConverter(input);
        object.recordFrequencies();
        object.frequenciesToTree();
        object.treeToCode();

        String encodedMessage = object.encodeMessage();
        System.out.println(encodedMessage + "\n");
        System.out.println("Message size in ASCII encoding: " + String.valueOf(input.length()*8));
        System.out.println("Message size in Huffman coding: " + String.valueOf(encodedMessage.length()) + "\n");
        
        String decodedMessage = object.decodeMessage(encodedMessage);
        System.out.println(decodedMessage);
    }
}