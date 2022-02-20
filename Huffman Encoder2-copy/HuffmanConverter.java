import java.io.*;
import java.lang.*;
import java.util.*;

public class HuffmanConverter{

    //​ ​The​ ​#​ ​of​ ​chars​ ​in​ ​the​ ​ASCII​ ​table​ ​dictates
    //​ ​the​ ​size​ ​of​ ​the​ ​count[]​ ​&​ ​code[]​ ​arrays.    
    public static final int NUMBER_OF_CHARACTERS = 256;
    
    //​ ​the​ ​contents​ ​of​ ​our​ ​message...
    private String contents;

    //​ ​the​ ​tree​ ​created​ ​from​ ​the​ ​msg
    private HuffmanTree huffmanTree;
    
    //​ ​tracks​ ​how​ ​often​ ​each​ ​character​ ​occurs
    private int count[];

    // list of individual elements in contents
    private String elements[];
    
    //​ ​the​ ​huffman​ ​code​ ​for​ ​each​ ​character
    private String code[];
    
    //​ ​stores​ ​the​ ​#​ ​of​ ​unique​ ​chars​ ​in​ ​contents
    private int uniqueChars = 0; //(optional)

    /**​ ​Constructor​ ​taking​ ​input​ ​String​ ​to​ ​be​ ​converted​ ​*/
    public HuffmanConverter(String input){
        this.contents = input;
        
        this.count = new int[NUMBER_OF_CHARACTERS];
        this.code = new String[NUMBER_OF_CHARACTERS];
        this.elements = new String[NUMBER_OF_CHARACTERS];
        
        for (int i = 0; i < this.elements.length; i++){
            this.elements[i] = "";

            this.count[i] = 0;
        }

    }

    /**
    *​ ​Records​ ​the​ ​frequencies​ ​that​ ​each​ ​character​ ​of​ ​our
    *​ ​message​ ​occurs...
    *​ ​I.e.,​ ​we​ ​use​ ​'contents'​ ​to​ ​fill​ ​up​ ​the​ ​count[]​ ​list... ​*/
    public void recordFrequencies(){

        int pointer = 0;

        List<String> al =
            new ArrayList<String>(Arrays.asList(this.elements));

        for (int i = 0; i < this.contents.length(); i++){

            if (!(al.contains(String.valueOf(this.contents.charAt(i))))){
                al.add(pointer,String.valueOf(this.contents.charAt(i)));
                this.elements[pointer] = String.valueOf(this.contents.charAt(i));
                pointer ++;

            }

        }

        for (int i = 0; i < this.contents.length(); i++){

            this.count[al.indexOf(String.valueOf(this.contents.charAt(i)))]++;
        }
        
       
    }

    /**
    *​ ​Converts​ ​our​ ​frequency​ ​list​ ​into​ ​a​ ​Huffman​ ​Tree.​ ​We​ ​do​
    *​ ​taking​ ​our​ ​count[]​ ​list​ ​of​ ​frequencies,​ ​and​ ​creating​ ​a​
    *​ ​heap​ ​in​ ​a​ ​manner​ ​similar​ ​to​ ​how​ ​a​ ​heap​ ​was​ ​made​ ​in​ ​HuffmanTree's
    *​ ​fileToHeap​ ​method.​ ​Then,​ ​we​ ​print​ ​the​ ​heap,​ ​and​ ​make​ ​a​ ​call​ ​to
    *​ ​HuffmanTree.heapToTree()​ ​method​ ​to​ ​get​ ​our​ ​much​ ​desired
    *​ ​HuffmanTree​ ​object,​ ​which​ ​we​ ​store​ ​as​ ​huffmanTree.
    */
    public void frequenciesToTree(){

        int e = 0;

        int length = 0;

        for (int i = 0; i < this.elements.length; i++){

            if (this.elements[i].equals("")){
                length = i + 1;
                break;                
            }
        }

        // creating an array of HuffmanNodes
        HuffmanNode[] arrayNodes = new HuffmanNode[length];

        // create a HuffmanNode with letter and frequency and adding it to the array
        for (int i = 0; i < length; i++){

            arrayNodes[e] = new HuffmanNode(this.elements[i], Double.valueOf(this.count[i]));
            e++;
            
            
        }
        
        // creating a binary heap using HuffmanNode array list
        BinaryHeap bheap = new BinaryHeap(arrayNodes);

        bheap.printHeap();

        System.out.println();

        this.huffmanTree = HuffmanTree.createFromHeap(bheap);

        this.huffmanTree.printLegend();

        System.out.println("\n");
    }

    /**
    *​ ​Iterates​ ​over​ ​the​ ​huffmanTree​ ​to​ ​get​ ​the​ ​code​ ​for​ ​each​ ​letter.
    *​ ​The​ ​code​ ​for​ ​letter​ ​i​ ​gets​ ​stored​ ​as​ ​code[i]...​ ​This​ ​method
    *​ ​behaves​ ​similarly​ ​to​ ​HuffmanTree's​ ​printLegend()​ ​method...
    *​ ​Warning:​ ​Don't​ ​forget​ ​to​ ​initialize​ ​each​ ​code[i]​ ​to​ ​""
    *​ ​BEFORE​ ​calling​ ​the​ ​recursive​ ​version​ ​of​ ​treeToCode...
    */
    public void treeToCode(){

        for (int i = 0; i < this.code.length; i++){

            this.code[i] = "";
            
        }

        treeToCode(huffmanTree.root, "");
    }

    /*
    *​ ​A​ ​private​ ​method​ ​to​ ​iterate​ ​over​ ​a​ ​HuffmanNode​ ​t​ ​using​ s, which
    *​ ​contains​ ​what​ ​we​ ​know​ ​of​ ​the​ ​HuffmanCode​ ​up​ ​to​ ​node​ ​t.​ This is
    *​ ​called​ ​by​ ​treeToCode(),​ ​and​ ​resembles​ ​the​ ​recursive​ ​printLegend
    *​ ​method​ ​in​ ​the​ ​HuffmanTree​ ​class.​ ​Note​ ​that​ ​when​ ​t​ ​is​ ​a​ ​leaf​ ​node,
    *​ ​t's​ ​letter​ ​tells​ ​us​ ​which​ ​index​ ​i​ ​to​ ​access​ ​in​ ​code[],​ ​and​ ​tells
    *​ ​us​ ​what​ ​to​ ​set​ ​code[i]​ ​to...
    */
    private void treeToCode(HuffmanNode t, String s){

        int index = 0;

        if (t.letter.length() > 1){

            treeToCode(t.left, s + "0");

            treeToCode (t.right, s + "1");
        }

        else if(t.letter.length() == 1){


            for (int i = 0; i < this.elements.length; i++){
                if (t.letter.equals(this.elements[i])) index = i;

            }
            this.code[index] = s;
        }

    }

    /**
    *​ ​Using​ ​the​ ​message​ ​stored​ ​in​ ​contents,​ ​and​ ​the​ ​huffman​ ​conversions
    *​ ​stored​ ​in​ ​code[],​ ​we​ ​create​ ​the​ ​Huffman​ ​encoding​ ​for​ ​our​ ​message
    *​ ​(a​ ​String​ ​of​ ​0's​ ​and​ ​1's),​ ​and​ ​return​ ​it...
    */
    public String encodeMessage(){
        
        String encodedMessage = "";
        int i, e;

        for (i = 0; i < this.contents.length(); i++){

            for (e = 0; e < this.elements.length; e++){

                if (this.elements[e].equals(String.valueOf(this.contents.charAt(i))))
                    encodedMessage += this.code[e];
            }
        }

        return encodedMessage;

    }

    /**
    *​ ​Reads​ ​in​ ​the​ ​contents​ ​of​ ​the​ ​file​ ​named​ ​filename​ ​and​ ​returns
    *​ ​it​ ​as​ ​a​ ​String.​ ​The​ ​main​ ​method​ ​calls​ ​this​ ​method​ ​on​ ​args[0]...
    */
    public static String readContents(String filename){

        String content = "";
        
        File file = new File(filename);

        try{
        
            Scanner sc = new Scanner(file);

            while(sc.hasNext()){
            content += (String) sc.nextLine();
            content += "\n";
            
                // if (sc.hasNext()) content += "\n";
            
            }
        
        }

        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }

        return content;
    }

    /**
    *​ ​Using​ ​the​ ​encoded​ ​String​ ​argument,​ ​and​ ​the​ ​huffman​ ​codings,
    *​ ​re-create​ ​the​ ​original​ ​message​ ​from​ ​our
    *​ ​huffman​ ​encoding​ ​and​ ​return​ ​it...
    ​*/
    public String decodeMessage(String encodedStr){

        String codedElement = "";

        String decodedMessage = "";

        for (int i = 0; i < encodedStr.length(); i++){

            codedElement += String.valueOf(encodedStr.charAt(i));

            for (int j = 0; j < this.elements.length; j++){

                if (codedElement.equals(this.code[j])){

                    decodedMessage += this.elements[j];
                    
                    codedElement = "";
                    
                    break;
                }                
            }
            
        }

        return decodedMessage;
    }

    /**
    * Uses​ ​args[0]​ ​as​ ​the​ ​filename,​ ​and​ ​reads​ ​in​ ​its​ ​contents.​ ​Then
    * instantiates​ ​a​ HuffmanConverter ​object,​ ​using​ ​its​ ​methods​ ​to
    * obtain​ ​our​ ​results​ ​and​ ​print​ ​the​ ​necessary​ ​output.​ ​Finally,
    * decode​ ​the​ ​message​ ​and​ ​compare​ ​it​ ​to​ ​the​ ​input​ ​file.<p>
    * NOTE:​ ​Example​ ​method​ ​provided​ ​below...​
    */
    public static void main(String args[]){

  

        String input = readContents(args[0]);
        
        HuffmanConverter huffmanConverter = new HuffmanConverter(input);

        huffmanConverter.recordFrequencies();
    
        huffmanConverter.frequenciesToTree();

        huffmanConverter.treeToCode();

        String encodedMessage = huffmanConverter.encodeMessage();

        System.out.println(encodedMessage + "\n");

        System.out.println("Message size in ASCII encoding: " + String.valueOf(input.length()*8));

        System.out.println("Message size in Huffman coding: " + String.valueOf(encodedMessage.length()) + "\n");

        String decodedMessage = huffmanConverter.decodeMessage(encodedMessage);

        System.out.println(decodedMessage);
    
    }

}












