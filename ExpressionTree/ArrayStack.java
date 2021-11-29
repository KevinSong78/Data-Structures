
/**
 * A specific type of Stack that utilizes an array to reference generic objects
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @author Kevin Song
 */
public class ArrayStack<E> implements Stack<E>
{
  public static final int CAPACITY=1000;   // default array capacity
  private E[] data;   // generic array used for storage
  private int t = -1;   // index of the top element in stack
  private int t2 = -2; // index of the element directly below the top in stack
    /** Constructs an empty stack using the default array capacity. */
  public ArrayStack() { this(CAPACITY); }  // constructs stack with default capacity

  /**
   * Constructs and empty stack with the given array capacity.
   * @param capacity length of the underlying array
   */
  @SuppressWarnings({"unchecked"})
  public ArrayStack(int capacity) {        // constructs stack with given capacity
    data = (E[]) new Object[capacity];     // safe cast; compiler may give warning
  }
  @Override
  public int size()
  { return (t + 1); }
  @Override
  public boolean isEmpty() 
  { return (t == -1); }
  @Override
  /**
   * Inserts an element at the top of the stack.
   * @param e   the element to be inserted
   * @throws IllegalStateException if the array storing the elements is full
   */
  public void push(E e) throws IllegalStateException {
    if (size() == data.length) throw new IllegalStateException("Stack is full");
    data[++t] = e;                           // increment t before storing new item
  }
  @Override
  /**
   * Looks at the top of the stack and returns it
   */
  public E top() {
    if (isEmpty()) return null;
    return data[t];
  }
  @Override
  public E pop() {
    if (isEmpty()) return null;
    E answer = data[t];
    data[t] = null;
    t--;
    return answer;
  }
}
