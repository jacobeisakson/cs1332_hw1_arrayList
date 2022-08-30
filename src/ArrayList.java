import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author JACOB ISAKSON
 * @version 1.0
 * @userid jisakson3
 * @GTID 903276645
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];  // declare new Object[] and cast it to T[]
        size = 0;  // capacity = INITIAL_CAPACITY but the size = 0
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {

        // -------------------------------
        // EXCEPTIONS AND SIZE CHECKS
        // -------------------------------

        if (data == null) {
            // Check for valid data input
            throw new IllegalArgumentException("'data' is null. A null input cannot be entered.");
        } else if ((index < 0) || (index > size)) {
            // Check for a valid index
            throw new IndexOutOfBoundsException("'index' is out of bounds. 'index' must be between 0 and " + size);
        } else {
            if (size > (backingArray.length - 1)) {
                // new array needs to be created and the old data transferred
                T[] transferArray = backingArray;  // create a transferArray to hold old values
                backingArray = (T[]) new Object[backingArray.length * 2];  // create a new ArrayList with double size

                // copy the transferArray into the new backingArray
                for (int i = 0; i < transferArray.length; i++) {
                    backingArray[i] = transferArray[i];  // place transferArray data at same index in backingArray
                }
            }

            // -------------------------------
            // ADDING DATA TO ARRAYLIST
            // -------------------------------

            // adding to the back
            if (index == size) {
                // adding to the back
                backingArray[size] = data;
            } else if (index == 0) {
                // adding to the front
                for (int i = size; i > 0; i--) {
                    // back to front to avoid deleting entries
                    backingArray[i] = backingArray[i - 1];  // shifts the value at [i-1] to index [i] (shifts to right)
                }
                backingArray[0] = data;
            } else {
                // adding anywhere in between the first and last index
                for (int i = size; i > index; i--) {
                    backingArray[i] = backingArray[i - 1];  // shifts the value at [i-1] to index [i] (shift to right)
                }
                backingArray[index] = data;
            }

            // adding a new data entry --> increase size of ArrayList by 1
            size = size + 1;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("'index' is out of bounds. 'index' must be between 0 and " + size);
        } else {
            // checks complete - remove
            if (index == size - 1) {
                T transfer = backingArray[--size];
                backingArray[size] = null;
                return transfer;
            } else {
                T transfer = backingArray[index];
                for (int i = index; i < size - 1; i++) {
                    backingArray[i] = backingArray[i + 1];
                }
                backingArray[--size] = null;
                return transfer;
            }

        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("method can't remove from an empty list");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("method can't remove from an empty list");
        }
        return removeAtIndex(size - 1);

    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("'index' is out of bounds. 'index' must be between 0 and " + size);
        } else {
            return backingArray[index];
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0); // if (size == 0) return true -> else return false (simplified for checkstyle)
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
