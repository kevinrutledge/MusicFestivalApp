package main;

/**
 * Defines a doubly-linked list class
 * @author Kevin Rutledge
 */
import java.util.NoSuchElementException;

public class LinkedList<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /**** CONSTRUCTORS ****/

    /**
     * Instantiates a new LinkedList with default values
     * @postcondition Creates an empty LinkedList
     * length set to 0 and first, last, and iterator references set to null.
     */
    public LinkedList() {
        length = 0;
        first = null;
        last = null;
        iterator = null;
    }

    /**
     * Converts the given array into a LinkedList
     * @param array the array of values to insert into this LinkedList
     * @postcondition Creates a LinkedList containing all elements from the array in the same order.
     */
    public LinkedList(T[] array) {
        this(); // Initialize the list to be empty
        for (T data : array) {
            addLast(data); // Add each element in the array to the list
        }
    }

    /**
     * Instantiates a new LinkedList by copying another List
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     * but separate, copy of the LinkedList original
     */
    public LinkedList(LinkedList<T> original) {
        this(); // Initialize the list to be empty
        Node temp = original.first; // Start at the beginning of the original list
        while (temp != null) {
            addLast(temp.data); // Copy each element to the new list
            temp = temp.next;
        }
    }

    /**** ACCESSORS ****/

    /**
     * Returns the value stored in the first node
     * @precondition LinkedList is not empty.
     * @return the value stored at node first
     * @throws NoSuchElementException if the LinkedList is empty.
     */
    public T getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return first.data;
    }

    /**
     * Returns the value stored in the last node
     * @precondition LinkedList must not be empty.
     * @return the value stored in the node last
     * @throws NoSuchElementException if the LinkedList is empty.
     */
    public T getLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return last.data;
    }

    /**
     * Returns the data stored in the iterator node
     * @precondition The iterator must be pointing to a valid node within the LinkedList.
     * @return the data stored in the iterator node
     * @throws NullPointerException if the iterator is pointing to null
     */
    public T getIterator() throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("Iterator is off end.");
        }
        return iterator.data;
    }

    /**
     * Returns the current length of the LinkedList
     * @return the length of the LinkedList from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the LinkedList is currently empty
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns whether the iterator is offEnd, i.e. null
     * @return whether the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }

    /**** MUTATORS ****/

    /**
     * Creates a new first element
     * @param data the data to insert at the front of the LinkedList
     * @postcondition A new node containing 'data' is added at the beginning of the LinkedList,
     * and length is incremented by 1.
     */
    public void addFirst(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) { // List is empty
            first = last = newNode; // newNode is the only node in the list
        } else { // List contains at least one node
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the end of the LinkedList
     * @postcondition A new node containing 'data' is added at the end of the LinkedList,
     * and length is incremented by 1.
     */
    public void addLast(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) { // List is empty
            first = last = newNode; // newNode is the only node in the list
        } else { // List contains at least one node
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        length++;
    }

    /**
     * Inserts a new element after the iterator.
     * @param data the data to insert
     * @precondition The iterator must be pointing to a valid node within the LinkedList.
     * @postcondition A new node containing 'data' is inserted in the LinkedList immediately after the node
     * at the iterator's current position, and length is incremented by 1.
     * @throws NullPointerException if the iterator is out of bounds.
     */
    public void addIterator(T data) throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("Iterator is off end. Cannot add.");
        }
        Node newNode = new Node(data);
        if (iterator == last) { // Inserting after the last element
            addLast(data);
        } else { // Inserting in the middle of the list
            newNode.next = iterator.next;
            newNode.prev = iterator;
            iterator.next.prev = newNode;
            iterator.next = newNode;
            length++;
        }
    }

    /**
     * Removes the element at the front of the LinkedList
     * @precondition LinkedList must not be empty.
     * @postcondition The first node is removed from the LinkedList, and length is decremented by 1.
     * @throws NoSuchElementException if the LinkedList is empty.
     */
    public void removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }
        if (first == last) { // Only one element in the list
            first = last = null;
        } else { // More than one element in the list
            first = first.next;
            first.prev = null;
        }
        length--;
    }

    /**
     * Removes the element at the end of the LinkedList
     * @precondition LinkedList must not be empty.
     * @postcondition The last node is removed from the LinkedList, and length is decremented by 1.
     * @throws NoSuchElementException if the LinkedList is empty.
     */
    public void removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }
        if (first == last) { // Only one element in the list
            first = last = null;
        } else { // More than one element in the list
            last = last.prev;
            last.next = null;
        }
        length--;
    }

    /**
     * Removes the element referenced by the iterator
     * @precondition The iterator must be pointing to a valid node within the LinkedList.
     * @postcondition The node at the iterator's current position is removed from the LinkedList,
     *                the iterator is set to null, and length is decremented by 1.
     * @throws NullPointerException if the iterator is pointing to null.
     */
    public void removeIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("Iterator is off end. Cannot remove.");
        }
        if (iterator == first) {
            removeFirst();
        } else if (iterator == last) {
            removeLast();
        } else {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            length--;
        }
        iterator = null; // Iterator is now off end
    }

    /**
     * Places the iterator at the first node
     * @precondition List must contain an element
     * @postcondition The iterator is positioned at the first node of the LinkedList.
     */
    public void positionIterator() {
        if (isEmpty()) throw new NoSuchElementException("List is empty.");
        iterator = first;
    }

    /**
     * Moves the iterator one node towards the last
     * @precondition The iterator must be pointing to a valid node within the LinkedList and not at the last node.
     * @postcondition The iterator is moved to the next node in the LinkedList.
     * @throws NullPointerException if the iterator is at the end of the LinkedList or the LinkedList is empty.
     */
    public void advanceIterator() throws NullPointerException {
        if (offEnd()) throw new NullPointerException("Iterator is off end.");
        iterator = iterator.next;
    }

    /**
     * Moves the iterator one node towards the first
     * @precondition The iterator must be pointing to a valid node within the LinkedList and not at the first node.
     * @postcondition The iterator is moved to the previous node in the LinkedList.
     * @throws NullPointerException if the iterator is at the start of the LinkedList or the LinkedList is empty.
     */
    public void reverseIterator() throws NullPointerException {
        if (offEnd()) throw new NullPointerException("Iterator is off end.");
        iterator = iterator.prev;
    }

    /**** ADDITIONAL OPERATIONS ****/

    /**
     * Re-sets LinkedList to empty as if the
     * default constructor had just been called
     */
    public void clear() {
        length = 0;
        first = last = iterator = null;
    }

    /**
     * Converts the LinkedList to a String, with each value separated by a
     * blank space. At the end of the String, place a new line character.
     * @return the LinkedList as a String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = first;
        while (temp != null) {
            result.append(temp.data).append(" ");
            temp = temp.next;
        }
        return result.toString() + "\n";
    }

    /**
     * Returns each element in the LinkedList along with its
     * numerical position from 1 to n.
     */
    public String numberedListString() {
        StringBuilder result = new StringBuilder();
        Node temp = first;
        int count = 1;
        while (temp != null) {
            result.append(count++).append(". ").append(temp.data).append("\n");
            temp = temp.next;
        }
        return result.toString();
    }

    /**
     * Determines whether the given Object is
     * another LinkedList, containing
     * the same data in the same order
     * @param obj another Object
     * @return whether there is equality
     */
    @SuppressWarnings("unchecked") //good practice to remove warning here
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LinkedList<?> other = (LinkedList<?>) obj;
        if (this.length != other.length) return false;

        Node currentThis = this.first;
        Node currentOther = (Node) other.first;
        while (currentThis != null && currentOther != null) {
            if (!currentThis.data.equals(currentOther.data)) {
                return false;
            }
            currentThis = currentThis.next;
            currentOther = currentOther.next;
        }
        return true;
    }

    /**CHALLENGE METHODS*/

    /**
     * Moves all nodes in the list towards the end
     * of the list the number of times specified
     * Any node that falls off the end of the list as it
     * moves forward will be placed the front of the list
     * For example: [1, 2, 3, 4, 5], numMoves = 2 -> [4, 5, 1, 2 ,3]
     * For example: [1, 2, 3, 4, 5], numMoves = 4 -> [2, 3, 4, 5, 1]
     * For example: [1, 2, 3, 4, 5], numMoves = 7 -> [4, 5, 1, 2 ,3]
     * @param numMoves the number of times to move each node.
     * @precondition numMoves >= 0
     * @postcondition iterator position unchanged (i.e. still referencing
     * the same node in the list, regardless of new location of Node)
     * @throws IllegalArgumentException when numMoves < 0
     */
    public void spinList(int numMoves) throws IllegalArgumentException {
        if (numMoves < 0) throw new IllegalArgumentException("numMoves must be >= 0");
        if (isEmpty() || numMoves % length == 0) return;

        numMoves = numMoves % length;
        for (int i = 0; i < numMoves; i++) {
            last.next = first;
            first.prev = last;
            first = last;
            last = last.prev;
            first.prev = null;
            last.next = null;
        }
    }

    /**
     * Splices together two LinkedLists to create a third List
     * which contains alternating values from this list
     * and the given parameter
     * For example: [1,2,3] and [4,5,6] -> [1,4,2,5,3,6]
     * For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6, 3, 4]
     * For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
     * @param list the second LinkedList
     * @return a new LinkedList, which is the result of
     * interlocking this and list
     * @postcondition this and list are unchanged
     */
    public LinkedList<T> altLists(LinkedList<T> list) {
        LinkedList<T> result = new LinkedList<>();
        Node currentThis = this.first;
        Node currentOther = list.first;
        while (currentThis != null || currentOther != null) {
            if (currentThis != null) {
                result.addLast(currentThis.data);
                currentThis = currentThis.next;
            }
            if (currentOther != null) {
                result.addLast(currentOther.data);
                currentOther = currentOther.next;
            }
        }
        return result;
    }

    /** MORE METHODS */

    /**
     * Determines at which index the iterator is located,
     * indexed from 0 to length - 1
     * @return the index number of the iterator.
     * @precondition Must position iterator before calling this method.
     * @throws NullPointerException when precondition is violated.
     */
    public int getIteratorIndex() throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("Iterator is off end. Cannot determine index.");
        }

        int index = 0;
        Node temp = first;
        while (temp != null) {
            if (temp == iterator) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        throw new RuntimeException("Iterator is in an invalid state.");
    }

    /**
     * Searches the LinkedList for a given element's index.
     * @param data the data whose index to locate.
     * @return the index of the data or -1 if the data is not contained
     * in the LinkedList.
     */
    public int findIndex(T data) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Advances the iterator to location within the LinkedList
     * specified by the given index.
     * @param index the index at which to place the iterator.
     * @precondition Must position iterator before calling this method.
     * @throws NullPointerException when the precondition is violated.
     */
    public void advanceIteratorToIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException("Index out of bounds.");
        iterator = first;
        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
        }
    }
}