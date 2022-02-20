/*
 * Developed for use with the book:
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 */
/**
 * A basic singly linked list implementation.
 */
public class SinglyLinkedList<E> implements Cloneable {
  //---------------- nested Node class ----------------
  /**
   * Node of a singly linked list, which stores a reference to its
   * element and to the subsequent node in the list (or null if this
   * is the last node).
   */
  private static class Node<E> {
    /** The element stored at this node */
    private E element;            // reference to the element stored at this node
    /** A reference to the subsequent node in the list */
    private Node<E> next;         // reference to the subsequent node in the list
    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> n) 
    {
      element = e;
      next = n;
    }
    
    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() 
    { 
        return element; 
    }
    
    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() 
    { 
        return next; 
    }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) 
    { 
        next = n; 
    }
  } //----------- end of nested Node class -----------

  // instance variables of the SinglyLinkedList
  /** The head node of the list */
  private Node<E> head = null;               // head node of the list (or null if empty)

  /** The last node of the list */
  private Node<E> tail = null;               // last node of the list (or null if empty)

  /** Number of nodes in the list */
  private int size = 0;                      // number of nodes in the list

  /** Constructs an initially empty list. */
  public SinglyLinkedList() 
  { 
  }              // constructs an initially empty list
  public int countDuplicates()
  {
     int duplicates = 0;
     Node walk = head; 

     while(walk.getNext() != null)
     {
         Node walk2 = head;
         while(walk2.getNext() != null)
         {
            if(walk.element == walk2.getNext())
             {
                 duplicates ++;
                 break;
             }
             walk2 = walk2.getNext();
         }
         walk = walk.getNext();
     }
      return duplicates;
  }
public int countEvens()
          {
              int evens = 0;
              Node walk = head;
              
              while (walk!= null)
              {
                  int element = (Integer)walk.getElement();
                  if(element % 2 == 0)
                  {
                      evens++;
                  }
                  walk = walk.getNext();
                }
              return evens;
          }  
          
        public static void main(String[] args)
        {
            
        }
  public boolean find(E value)
  {
      if (value == null)
      {
          return false;
      }
      /*  if(isEmpty()) // not necessary here
          {
              return false;
          }
      */
      Node walk = head; // if list is empty, walk will be equal to null,
                        // skipping the while loop and returning false
      while(walk!= null)
      {
          if (walk.getElement().equals(value)) //for objects, == doesn't work
                         // because == only works with primitives. Testing
                        // == with objects only checks the references, not
                        // if the two objects are equal.
          {
              return true;
            }
          walk=walk.getNext();
      }
      return false;
  }
  
  public boolean insertAfter (E valueToInsertAfter, E newValue)
  {
      Node walk = head;
      Node<E> position = locate(valueToInsertAfter);
      if (position == null)
      {
          return false;
      }
      else
      {
          Node<E> newNode = new Node<E> (newValue, position.getNext());
          position.setNext(newNode);
          if(position == tail)
          {
              tail = newNode;
          }
          size ++;
          return true;
      }
  }
  
  private Node<E> locate(E value)
  {
      Node<E> walk = head;
      while(walk.getElement().equals(value) == false)
      {
         walk = walk.getNext();
      }
      if(walk.getElement().equals(value))
      {
          return walk;
      }
      return null;
  }
  
  public boolean insertBefore(E valueToInsertBefore, E newValue)
  {
      if(head.getElement().equals(valueToInsertBefore))
      {
          Node<E> newNode = new Node<E>(newValue, head);
          
      }
      //create 2 nodes, one that is basically the locate method. 
      // THe second node trails directly behind and is returned when the 
      // first finds the right node.
      Node<E> walk = head;
      Node<E> walk2 = head;
      while(walk.getElement().equals(valueToInsertBefore) == false)
      {
          walk2 = walk;
          walk = walk.getNext();
      }
      if(walk.getElement().equals(valueToInsertBefore))
      {
          Node<E> newNode = new Node<E>(newValue, walk2.getNext());
          walk2.setNext(newNode);
      }
      return true;
  }
  
  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size()
  { 
      return size; 
  }

   /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() 
  { 
      return size == 0; 
  }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() 
  {             // returns (but does not remove) the first element
    if (isEmpty()) 
        return null;
    return head.getElement();
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {              // returns (but does not remove) the last element
    if (isEmpty()) 
        return null;
    return tail.getElement();
  }

  // update methods
  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e) 
  {                // adds element e to the front of the list
    head = new Node<>(e, head);              // create and link a new node
    if (size == 0)
      tail = head;                           // special case: new node becomes tail also
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) 
  {                 // adds element e to the end of the list
    Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
    if (isEmpty())
      head = newest;                         // special case: previously empty list
    else
      tail.setNext(newest);                  // new node after existing tail
    tail = newest;                           // new node becomes the tail
    size++;
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() 
  {                   // removes and returns the first element
    if (isEmpty()) 
        return null;              // nothing to remove
    E answer = head.getElement();
    head = head.getNext();        // will become null if list had only one node
    size--;
    if (size == 0)
        tail = null;                      // special case as list is now empty
    return answer;
  }
   
  public E removeLast() 
  {                   // removes and returns the last element
    if (isEmpty()) 
      return null;              // nothing to remove
    
    if (size == 1)
      return removeFirst();

    E answer = tail.getElement();
    Node walk = head; 
    while (walk.getNext() != tail) 
      walk = walk.getNext();     // will become null if list had only one node
     // we need to change the reference from the second to last node, as well as
     // setting the tail to null. The reference needs to change to null, and 
     // the second to last node must now become the tail. That's why 
     // we need to walk through it as we can't walk from the back.
    tail = walk;
    tail.setNext(null);
    size--; // because now the list is one node smaller
    return answer;
  }
  @SuppressWarnings({"unchecked"})
  public boolean equals(Object o) // has to be Object o in order to override the 
                                  // super object method
  {
    if (o == null) 
        return false;
    if (this.getClass() != o.getClass()) 
        return false;
    SinglyLinkedList other = (SinglyLinkedList) o;   // use nonparameterized type
    if (size != other.size) 
        return false;
    Node walkA = head;                               // traverse the primary list
    Node walkB = other.head;                         // traverse the secondary list
    while (walkA != null)
    {
      if (!walkA.getElement().equals(walkB.getElement())) 
          return false; //mismatch
      walkA = walkA.getNext();
      walkB = walkB.getNext();
    }
    return true;   // if we reach this, everything matched successfully
  }

  @SuppressWarnings({"unchecked"})
  public SinglyLinkedList<E> clone() throws CloneNotSupportedException 
  {
    // always use inherited Object.clone() to create the initial copy
    SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
    if (size > 0) 
    {                    // we need independent chain of nodes
      other.head = new Node<>(head.getElement(), null);
      Node<E> walk = head.getNext();      // walk through remainder of original list
      Node<E> otherTail = other.head;     // remember most recently created node
      while (walk != null) 
      {              // make a new node storing same element
        Node<E> newest = new Node<>(walk.getElement(), null);
        otherTail.setNext(newest);     // link previous node to this one
        otherTail = newest;
        walk = walk.getNext();
      }
    }
    return other;
  }

  public int hashCode() // not covered
  {
    int h = 0;
    for (Node walk=head; walk != null; walk = walk.getNext()) 
    {
      h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
      h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
    }
    return h;
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() 
  {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) 
    {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }
}