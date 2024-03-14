package main;

/**
 * @author Jennifer Parrish
 * CIS 22C, Lab 7
 * IMPORTANT: DO NOT ALTER THIS FILE
 */
import java.util.NoSuchElementException;

public interface Q<T> {
    /**
     * Returns the value stored at the front of the Queue.
     * @return the value at the front of the queue.
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the precondition is violated.
     */
    T getFront() throws NoSuchElementException;

    /**
     * Returns the size of the Queue.
     * @return the size from 0 to n.
     */
    int getSize();

    /**
     * Determines whether a Queue is empty.
     * @return whether the Queue contains no elements.
     */
    boolean isEmpty();

    /**
     * Inserts a new value at the end of the Queue.
     * @param data the new data to insert.
     * @postcondition a new node at the end of the Queue.
     */
    void enqueue(T data);

    /**
     * Removes the front element in the Queue.
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the precondition is violated.
     * @postcondition the front element has been removed.
     */
    void dequeue() throws NoSuchElementException;
}