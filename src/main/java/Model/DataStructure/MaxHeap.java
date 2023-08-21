package Model.DataStructure;

/**
 * Class where we perform our own implementation of what a maxHeap would be.
 *
 * @param <T> Generic data type
 */
public class MaxHeap<T extends Comparable<T>> {
    private T[] data;
    private int size;

    /**
     * Constructor method where we initialize the maximum capacity of this heap.
     *
     * @param capacity the max capacity
     */
    public MaxHeap(int capacity) {
        data = (T[]) new Comparable[capacity];
        size = 0;
    }

    /**
     * Method that returns the data array.
     *
     * @return the data array.
     */
    public T[] getData() {
        return data;
    }

    /**
     * Method that returns the priority value of the heap without removing it.
     *
     * @return the priority value
     */
    public T peek() {
        if (size != 0) {
            return data[0];
        }
        return null;
    }

    /**
     * Method that inserts a new value into the array.
     * Within it, rebalancing methods are used.
     *
     * @param value the new value
     */
    public void add(T value) {
        if (size != data.length) {
            data[size] = value;
            size++;
            siftUp(size - 1);
        }
    }

    /**
     * Method that removes the priority value from the heap.
     * Inside the same method, the rebalancing is performed to ensure it continues to satisfy the max heap property.
     *
     * @return the priority value
     */
    public T remove() {
        if (size != 0) {
            T value = data[0];
            data[0] = data[size - 1];
            size--;
            siftDown(0);
            return value;
        }
        return null;
    }

    /**
     * Method that performs an upward rebalancing within the heap.
     *
     * @param index Index of the value being rebalanced.
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (data[parentIndex].compareTo(data[index]) >= 0) {
                break;
            }
            swap(parentIndex, index);
            index = parentIndex;
        }
    }

    /**
     * Method that performs a downward rebalancing within the heap.
     *
     * @param index Index of the value being rebalanced.
     */
    private void siftDown(int index) {
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if (leftChildIndex >= size) {
                break;
            }
            int maxChildIndex = leftChildIndex;
            if (rightChildIndex < size && data[rightChildIndex].compareTo(data[leftChildIndex]) > 0) {
                maxChildIndex = rightChildIndex;
            }
            if (data[index].compareTo(data[maxChildIndex]) >= 0) {
                break;
            }
            swap(index, maxChildIndex);
            index = maxChildIndex;
        }
    }

    /**
     * Method that swaps the positions of two values.
     *
     * @param i the first value
     * @param j the second value
     */
    private void swap(int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Method that prints the list of data in the heap.
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * Method that checks if the list contains a given value.
     *
     * @param value the given value
     * @return The existence of the value.
     */
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that removes a specific value from the heap.
     *
     * @param value the specific value
     * @return if the value could be removed
     */
    public boolean removeValue(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                data[i] = data[size - 1];
                size--;
                siftDown(i);
                siftUp(i);
                return true;
            }
        }
        return false;
    }
}
