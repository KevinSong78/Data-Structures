/**
 * A basic singly linked list implementation, utilizing a dummy node instead.
 * The methods have not been changed yet besides First()
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class SingleLinkedList<E> implements Cloneable {

  public static void main (String [] args)
  {
	  SinglyLinkedList<Integer> l = new SinglyLinkedList<Integer>();

    System.out.println(l);
  
 
    System.out.println(l.insertAfter(100, 100));

    l.addFirst(9);
    System.out.println(l);
    System.out.println(l.insertAfter(9, 100));
    System.out.println(l);
    System.out.println(l.insertAfter(9, 10));
    System.out.println(l);
    System.out.println(l.insertAfter(100, 100));
    System.out.println(l);
    System.out.println(l.insertAfter(10, 20));

    /*
    l.addFirst(9);
    System.out.println(l.insertBefore(9, 100));
    System.out.println(l);
    System.out.println(l.insertBefore(9, 10));
    System.out.println(l);
    System.out.println(l.insertBefore(100, 1000));
    System.out.println(l);
    System.out.println(l.insertBefore(10, 20));
    System.out.println(l);
      */

    //l.insertBefore(100, 9);
    /*
    l.addFirst(1);
    l.addFirst(6);
    l.addFirst(3);
      */
    /*
    System.out.println(l);
    System.out.println("l.find(6): " + l.find(6));
    System.out.println("l.find(4): " + l.find(4));
    System.out.println("l.find(9): " + l.find(9));
    System.out.println("l.find(3): " + l.find(3));
    */
    /*
      l.insertAfter(9, 10);
    l.insertAfter(1, 10);
    l.insertAfter(6, 10);
    l.insertAfter(8, 10);
    l.insertAfter(3, 10);
    l.insertAfter(100, 100);
     */
    /* 
    l.insertBefore(9, 10);
    l.insertBefore(1, 10);
    l.insertBefore(6, 10);
    l.insertBefore(8, 10);
    l.insertBefore(3, 10);
     */
      System.out.println(l);
  	
  }


  public boolean find(E value) 
  { 
    Node<E> walk = head;
    while (walk != null)
    {
	    if (walk.getElement().equals(value))
		    return true;
  		walk = walk.getNext();
  	}     
  	return false;
  }


  public boolean insertAfter(E valueToInsertAfter, E newValue)
  {
	  Node<E> position = locate(valueToInsertAfter); 
  	if (position == null)
  	  return false;
	 else 
	 {
	    Node<E> newest = new Node<E>(newValue, position.getNext());
			position.setNext(newest); 
			if (position == tail)
				tail = newest;
				
   	  size++;
			return true;	
		}
  } 

  private Node<E> locate(E value)
  {
	  Node<E> walk = head;
  	while (walk != null)
  	{
		  if (walk.getElement().equals(value))
			  return walk;
  		walk = walk.getNext();
  	}     
  	return null;	
  }

  private Node<E> locatePrior(E value)
  {
	  Node<E> walk = head;
  	
    if (head == null) // special case empty list
      return null;

    while (walk.getNext() != null)
  	{
	    if (walk.getNext().getElement().equals(value))
	      return walk;
      walk = walk.getNext();
    }     
    return null;	
  }

  public boolean insertBefore(E valueToInsertBefore, E newValue)
  {
	  // list is empty
  	if (size == 0)
  	   return false;

  	// valueToInsertBefore is first in list

  	if (head.getElement().equals(valueToInsertBefore))
  	{
		Node<E> newest = new Node<E>(newValue, head);
  		head = newest;
  		size++;
        	return true;
  	}

  	Node<E> position = locatePrior(valueToInsertBefore); 

  	// valueToInsertBefore not in the list
  	if (position == null)
  	    return false;

  	 //all other cases - found prior node - not the head
  	 Node<E> newest = new Node<E>(newValue, position.getNext());
  	 position.setNext(newest); 
  	 size++;
  	 return true;	
  } 
  


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
    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the SingleLinkedList
  /** The head node of the list */
  private Node<E> head = new Node<E>(null, null);   
  
  /** The last node of the list */
  private Node<E> tail = null;               // last node of the list (or null if empty)

  /** Number of nodes in the list */
  private int size = 0;                      // number of nodes in the list

  /** Constructs an initially empty list. */
  public SingleLinkedList() { }              // constructs an initially empty list

  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() {             // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return head.getNext().getElement(); // This is to skip the dummy nodes.
    // Essentially, you'll have to look one ahead for all the methods.
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {              // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }

  // update methods
  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e) {                // adds element e to the front of the list
    head = new Node<>(e, head);              // create and link a new node
    if (size == 0)
      tail = head;                           // special case: new node becomes tail also
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) {                 // adds element e to the end of the list
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
  public E removeFirst() {                   // removes and returns the first element
    if (isEmpty()) return null;              // nothing to remove
    E answer = head.getElement();
    head = head.getNext();                   // will become null if list had only one node
    size--;
    if (size == 0)
      tail = null;                           // special case as list is now empty
    return answer;
  }

  @SuppressWarnings({"unchecked"})
  public boolean equals(Object o) {
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    SingleLinkedList other = (SingleLinkedList) o;   // use nonparameterized type
    if (size != other.size) return false;
    Node walkA = head;                               // traverse the primary list
    Node walkB = other.head;                         // traverse the secondary list
    while (walkA != null) {
      if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
      walkA = walkA.getNext();
      walkB = walkB.getNext();
    }
    return true;   // if we reach this, everything matched successfully
  }

  @SuppressWarnings({"unchecked"})
  public SingleLinkedList<E> clone() throws CloneNotSupportedException {
    // always use inherited Object.clone() to create the initial copy
    SingleLinkedList<E> other = (SingleLinkedList<E>) super.clone(); // safe cast
    if (size > 0) {                    // we need independent chain of nodes
      other.head = new Node<>(head.getElement(), null);
      Node<E> walk = head.getNext();      // walk through remainder of original list
      Node<E> otherTail = other.head;     // remember most recently created node
      while (walk != null) {              // make a new node storing same element
        Node<E> newest = new Node<>(walk.getElement(), null);
        otherTail.setNext(newest);     // link previous node to this one
        otherTail = newest;
        walk = walk.getNext();
      }
    }
    return other;
  }

  public int hashCode() {
    int h = 0;
    for (Node walk=head; walk != null; walk = walk.getNext()) {
      h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
      h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
    }
    return h;
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }
}