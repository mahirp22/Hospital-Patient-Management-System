/**
 * MinHeap class
 * 
 * This class implements a Min-Heap using an array.
 * It is used to manage EMERGENCY patients.
 * 
 * In this project:
 * - lower severity value = higher priority
 * - severity 1 is more urgent than severity 5
 * 
 * Because this is a MIN-HEAP:
 * the smallest severity value should always be at the root.
 */
public class MinHeap {

    // Array used to store Patient objects in heap form
    private Patient[] heap;

    // Current number of patients stored in the heap
    private int size;

    // Maximum number of patients the heap can hold
    private int capacity;


    /**
     * Constructor
     * 
     * Creates an empty heap with a fixed capacity.
     */
    public MinHeap(int capacity) {
        this.capacity = capacity;
        heap = new Patient[capacity];
        size = 0;
    }


    /**
     * Returns true if heap has no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Returns true if heap is full
     */
    public boolean isFull() {
        return size == capacity;
    }


    /**
     * Returns number of patients currently in heap
     */
    public int size() {
        return size;
    }


    /**
     * Inserts a new emergency patient into the heap
     * 
     * Steps:
     * 1. Put patient at end of array
     * 2. Restore heap order by moving patient upward if needed
     */
    public void insert(Patient patient) {

        // Check overflow condition
        if (isFull()) {
            System.out.println("Emergency patient heap is full.");
            return;
        }

        // Place patient at next available position
        heap[size] = patient;

        /**
         * Restore heap property:
         * if inserted patient has smaller severity than parent,
         * move it upward.
         */
        heapifyUp(size);

        // Increase total size after insertion
        size++;
    }


    /**
     * Removes and returns the minimum element (highest-priority patient)
     * 
     * In a min-heap, the minimum element is always at index 0.
     * 
     * Steps:
     * 1. Save root
     * 2. Replace root with last element
     * 3. Remove last element
     * 4. Restore heap order by moving root downward
     */
    public Patient removeMin() {

        // Check underflow condition
        if (isEmpty()) {
            return null;
        }

        // Root is the minimum / highest-priority patient
        Patient min = heap[0];

        // Move last element to root
        heap[0] = heap[size - 1];

        // Clear old last position
        heap[size - 1] = null;

        // Reduce heap size
        size--;

        /**
         * Only heapify down if heap still contains elements
         */
        if (!isEmpty()) {
            heapifyDown(0);
        }

        return min;
    }


    /**
     * Returns the minimum element without removing it
     * 
     * This lets us see who will be served next.
     */
    public Patient peekMin() {
        if (isEmpty()) {
            return null;
        }

        return heap[0];
    }


    /**
     * heapifyUp restores heap order after insertion
     * 
     * Starting from inserted node:
     * - compare with parent
     * - if smaller, swap
     * - continue until correct position is found
     */
    private void heapifyUp(int index) {

        while (index > 0) {

            // Parent index formula for array-based heap
            int parent = (index - 1) / 2;

            /**
             * Compare severity values:
             * smaller severity = higher priority
             */
            if (heap[index].getSeverityLevel() < heap[parent].getSeverityLevel()) {
                swap(index, parent);
                index = parent; // continue checking upward
            } else {
                break; // heap property satisfied
            }
        }
    }


    /**
     * heapifyDown restores heap order after removing root
     * 
     * Starting from root:
     * - compare with left and right children
     * - swap with smallest child if needed
     * - continue until correct position is found
     */
    private void heapifyDown(int index) {

        while (true) {

            // Left child index
            int left = 2 * index + 1;

            // Right child index
            int right = 2 * index + 2;

            // Assume current node is smallest
            int smallest = index;

            /**
             * If left child exists and has smaller severity,
             * update smallest
             */
            if (left < size &&
                heap[left].getSeverityLevel() < heap[smallest].getSeverityLevel()) {
                smallest = left;
            }

            /**
             * If right child exists and has smaller severity,
             * update smallest
             */
            if (right < size &&
                heap[right].getSeverityLevel() < heap[smallest].getSeverityLevel()) {
                smallest = right;
            }

            /**
             * If one of the children is smaller than current node,
             * swap and continue downward
             */
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break; // heap property restored
            }
        }
    }


    /**
     * Swaps two elements in the heap array
     */
    private void swap(int i, int j) {
        Patient temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    /**
     * Displays patients in the heap array
     * 
     * IMPORTANT:
     * This displays heap-array order, NOT fully sorted order.
     * 
     * That is normal for a heap.
     */
    public void displayHeap() {

        if (isEmpty()) {
            System.out.println("No emergency patients waiting.");
            return;
        }

        System.out.println("Emergency Patients (heap order):");

        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }
    }


    /**
     * Returns all patients currently stored in heap
     * 
     * Useful for searching or other processing.
     */
    public Patient[] getAllPatients() {
        Patient[] patients = new Patient[size];

        for (int i = 0; i < size; i++) {
            patients[i] = heap[i];
        }

        return patients;
    }
}