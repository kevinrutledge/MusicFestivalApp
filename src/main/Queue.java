package main;

/**
 * The Queue class definition
 * @author Kevin Rutledge
 */
import java.util.NoSuchElementException;

public class Queue<T> implements Q<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node front;
    private Node end;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Queue class
     * @postcondition a new Queue object with all fields
     * assigned default values
     */
    public Queue() {
        front = end = null;
        size = 0;
    }

    /**
     * Converts an array into a Queue
     * @param array the array to copy into
     * the Queue
     */
    public Queue(T[] array) {
        for (T data : array) {
            enqueue(data);
        }
    }

    /**
     * Copy constructor for the Queue class
     * Makes a deep copy of the parameter
     * @param original the Queue to copy
     * @postcondition a Queue object clone with
     * matching elements and size
     */
    public Queue(Queue<T> original) {
        if (original.size > 0) {
            Node temp = original.front;
            while (temp != null) {
                enqueue(temp.data);
                temp = temp.next;
            }
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored at the front
     * of the Queue
     * @return the value at the front of the queue
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T getFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return front.data;
    }

    /**
     * Returns the size of the Queue
     * @return the size from 0 to n
     */
    public int getSize() {
        return size;
    }

    /**
     * Determines whether a Queue is empty
     * @return whether the Queue contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the end of the Queue
     *
     * @param data the new data to insert
     * @postcondition New data added to the end. Size increased by one.
     */
    public void enqueue(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = end = newNode;
        } else {
            end.next = newNode;
            end = newNode;
        }
        size++;
    }

    /**
     * Removes the front element in the Queue
     * @precondition Queue not empty.
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition Front element removed. Size decreased by one.
     */
    public void dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        if (size == 1) {
            front = end = null;
        } else {
            front = front.next;
        }
        size--;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Queue
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Queue values
     */
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = front;
        while (temp != null) {
            result.append(temp.data).append(" ");
            temp = temp.next;
        }
        return result + "\n";
    }

    /**
     * Determines whether two Queues contain
     * the same values in the same order
     * @param obj the Object to compare to this
     * @return whether obj and this are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object obj)  {
        if (this == obj) return true;
        if (!(obj instanceof Queue)) return false;
        Queue<?> other = (Queue<?>) obj;
        if (this.size != other.size) return false;
        Node currentThis = this.front;
        Node currentOther = (Node) other.front;
        while (currentThis != null) {
            if (!currentThis.data.equals(currentOther.data)) {
                return false;
            }
            currentThis = currentThis.next;
            currentOther = currentOther.next;
        }
        return true;
    }
}