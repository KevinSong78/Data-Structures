// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array
    /**
     * Construct the binary heap.
     */
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }
    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }
    /**
     * Construct the binary heap given an array of items.
      */
    public BinaryHeap( AnyType [ ] items )
    {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];
        int i = 1;
        for( AnyType item : items )
        {
            array[ i++ ] = item;
        }
        buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );
            // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }
    /**
     * Enlarges the array, used for inserting.
     * @param newSize the length of the new array
     */
    private void enlargeArray( int newSize )
    {
    AnyType [] old = array;
    array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];      
    }
    /**
     * Accessor method for currentSize
     */
    public int getSize()
    {
        return currentSize;
    }
    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }
    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }
    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
        {
            percolateDown( i );
        }
    }
    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }
    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole ) // this is altered according to the compareTo method in HuffmanNode
    {
        int child;
        AnyType tmp = array[ hole ];
        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) == 1 ) // if the right child is smaller than the left child, then we move the right child
                child++;
            if( array[ child ].compareTo( tmp ) == 1 ) // If child is smaller than the hole, then it needs to be moved
            {   
                array[ hole ] = array[ child ];
            }
            else
                break;
        }
        array[ hole ] = tmp;
    }
    /**
     * Prints out the binary heap according to the array order
     */
    public void printHeap()
    {
        System.out.println("The binary heap: ");
        for(int i = 1; i < array.length; i++)
        {
            if(array[i] == null)
            {
                break;
            }
            System.out.print(" " + array[i]);
        }
    }
}