import java.util.ArrayList;
/**
 * Write a description of class Generics here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Generics<E>
{
    public static void main(String [] args)
    {
        /**
         * Old Java
         */
        ArrayList strings = new ArrayList();
        strings.add("hello");
        //strings.add(new Integer(9));
        String word = (String) strings.get(0);
        //String word2 = (String) strings.get(1); // doesn't work
        System.out.println(word);
        /** 
         * New Java with generics
         */
        ArrayList<String>strings2 = new ArrayList<String>();
        strings2.add("hello");
        // strings2.add(new Integer(9)); // now the compiler cathes this
        String word2 = strings2.get(0); //also note here we don't have to 
                                        //cast to a string
        System.out.println(word2);
        System.out.println(strings2);
    }
    
    public void push(E e)
    {
       
    }
}
