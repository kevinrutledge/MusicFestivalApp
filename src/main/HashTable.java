package main;

/**
 * HashTable.java
 * @author Kevin Rutledge
 */
import java.util.ArrayList;

public class HashTable<T> {

    private int numElements;
    private ArrayList<LinkedList<T> > table;

    /**
     * Constructor for the HashTable class. Initializes the Table to be sized
     * according to value passed in as a parameter. Inserts size empty Lists into
     * the table. Sets numElements to 0
     *
     * @param size the table size
     * @precondition size > 0
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }
        this.table = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            this.table.add(new LinkedList<>()); // Initialize each bucket with an empty LinkedList
        }
        this.numElements = 0;
    }

    /**
     * Constructor for HashTable class.
     * Inserts the contents of the given array
     * into the Table at the appropriate indices
     * @param array an array of elements to insert
     * @param size the size of the Table
     * @precondition size > 0
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(T[] array, int size) throws IllegalArgumentException {
        this(size); // Initialize the table with the specified size

        if (array != null) { // Only proceed if the array is not null
            for (T element : array) {
                if (element != null) { // Check to ensure elements are not null
                    add(element); // Add each element to the hash table
                }
            }
        }
    }

    /** Accessors */

    /**
     * Returns the hash value in the table for a given Object.
     * @param obj the Object
     * @return the index in the table
     */
    private int hash(T obj) {
        int code = obj.hashCode();
        return code % table.size();
    }

    /**
     * Counts the number of elements at this index.
     * @param index the index in the table
     * @precondition index must be within the bounds of the table (0 <= index < table.size()).
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException when the precondition is violated
     */
    public int countBucket(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= table.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return table.get(index).getLength();
    }

    /**
     * Determines total number of elements in the table
     * @return total number of elements
     */
    public int getNumElements() {
        return numElements;
    }

    /**
     * Accesses a specified key in the Table
     * @param elmt the key to search for
     * @return the value to which the specified key is mapped,
     * or null if this table contains no mapping for the key.
     * @precondition elmt != null
     * @throws NullPointerException when the precondition is violated.
     */
    public T get(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element to find must not be null.");
        }
        int index = hash(elmt);
        LinkedList<T> bucket = table.get(index);

        if (bucket.isEmpty()) {
            return null;
        }

        bucket.positionIterator();
        while (!bucket.offEnd()) {
            if (bucket.getIterator().equals(elmt)) {
                return bucket.getIterator();
            }
            bucket.advanceIterator();
        }
        return null;
    }

    /**
     * Accesses a specified element in the table.
     * @param elmt the element to locate
     * @return the bucket number where the element
     * is located or -1 if it is not found.
     * @precondition elmt must not be null. Method determines the bucket where elmt may be located.
     * @throws NullPointerException when the precondition is violated.
     */
    public int find(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element to find must not be null.");
        }
        int index = hash(elmt);
        LinkedList<T> bucket = table.get(index);

        if (bucket.isEmpty()) {
            return -1;
        }

        bucket.positionIterator();
        while (!bucket.offEnd()) {
            if (bucket.getIterator().equals(elmt)) {
                return index;
            }
            bucket.advanceIterator();
        }
        return -1;
    }

    /**
     * Determines whether a specified element is in the table.
     * @param elmt the element to locate
     * @return whether the element is in the table
     * @precondition elmt must not be null. Checks if the table contains the specified element.
     * @throws NullPointerException when the precondition is violated
     */
    public boolean contains(T elmt) throws NullPointerException {
        return find(elmt) != -1;
    }

    /** Mutators */

    /**
     * Inserts a new element in the table at the end of the chain
     * of the correct bucket.
     * @param elmt the element to insert
     * @precondition elmt must not be null. Adds elmt to its corresponding bucket in the table.
     * @throws NullPointerException when the precondition is violated.
     */
    public void add(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element cannot be null.");
        }
        int index = hash(elmt);
        LinkedList<T> bucket = table.get(index);

        if (!bucket.equals(elmt)) { // Assuming equals method checks if element exists
            bucket.addLast(elmt);
            numElements++;
        }
    }

    /**
     * Removes the given element from the table.
     * @param elmt the element to remove
     * @precondition elmt must not be null. Tries to remove elmt from its bucket in the table.
     * @return whether elmt exists and was removed from the table
     * @throws NullPointerException when the precondition is violated
     */
    public boolean delete(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element cannot be null.");
        }
        int index = hash(elmt);
        LinkedList<T> bucket = table.get(index);

        bucket.positionIterator();
        while (!bucket.offEnd()) {
            if (bucket.getIterator().equals(elmt)) {
                bucket.removeIterator();
                numElements--;
                return true;
            }
            bucket.advanceIterator();
        }
        return false;
    }

    /**
     * Resets the hash table back to the empty state, as if the one argument
     * constructor has just been called.
     */
    public void clear() {
        for (LinkedList<T> list : table) {
            list.clear();
        }
        numElements = 0;
    }

    /** Additional Methods */

    /**
     * Computes the load factor.
     * @return the load factor
     */
    public double getLoadFactor() {
        return (double) numElements / table.size();
    }

    /**
     * Creates a String of all elements at a given bucket
     * @param bucket the index in the table
     * @return a String of elements, separated by spaces with a new line character
     *         at the end
     * @precondition bucket index must be within the bounds of the table (0 <= bucket < table.size()).
     *               Converts contents of the specified bucket to a String
     * @throws IndexOutOfBoundsException when bucket is
     * out of bounds
     */
    public String bucketToString(int bucket) throws IndexOutOfBoundsException {
        if (bucket < 0 || bucket >= table.size()) {
            throw new IndexOutOfBoundsException("Bucket index is out of bounds.");
        }
        return table.get(bucket).toString();
    }

    /**
     * Creates a String of the bucket number followed by a colon followed by
     * the first element at each bucket followed by a new line. For empty buckets,
     * add the bucket number followed by a colon followed by empty.
     *
     * @return a String of all first elements at each bucket.
     */
    public String rowToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.size(); i++) {
            sb.append("Bucket ").append(i).append(": ");
            LinkedList<T> bucket = table.get(i);
            if (bucket.isEmpty()) {
                sb.append("empty");
            } else {
                T firstElement = bucket.getFirst();
                sb.append(firstElement != null ? firstElement.toString() : "empty");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Starting at the 0th bucket, and continuing in order until the last
     * bucket, concatenates all elements at all buckets into one String, with
     * a new line between buckets and one more new line at the end of the
     * entire String.
     * @return a String of all elements in this HashTable.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.size(); i++) {
            LinkedList<T> bucket = table.get(i);
            if (!bucket.isEmpty()) {
                sb.append(bucket.toString());
            }
        }
        if (sb.length() > 0) {
            sb.append("\n");
        }
        return sb.toString();
    }
}