/**
 * CircularQueue class
 * 
 * This class implements a circular queue using an array.
 * It is used to manage REGULAR patients (first come, first served).
 * 
 * FIFO principle:
 * First In → First Out
 */
public class CircularQueue {

    // Array to store Patient objects
    private Patient[] queue;

    // Index of the FRONT of the queue (where we remove from)
    private int front;

    // Index of the REAR of the queue (where we insert into)
    private int rear;

    // Number of elements currently in the queue
    private int count;

    // Maximum size of the queue
    private int capacity;


    /**
     * Constructor
     * 
     * Initializes the queue with a fixed capacity.
     */
    public CircularQueue(int capacity) {
        this.capacity = capacity;

        // Create array to hold patients
        queue = new Patient[capacity];

        // Initially:
        front = 0;     // front starts at index 0
        rear = -1;     // rear starts at -1 (so first insert becomes 0)
        count = 0;     // queue is empty
    }


    /**
     * Checks if queue is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }


    /**
     * Checks if queue is full
     */
    public boolean isFull() {
        return count == capacity;
    }


    /**
     * Returns number of patients in queue
     */
    public int size() {
        return count;
    }


    /**
     * Adds a patient to the queue (enqueue operation)
     * 
     * Steps:
     * 1. Check if full
     * 2. Move rear forward (circularly)
     * 3. Insert patient
     * 4. Increase count
     */
    public void enqueue(Patient patient) {

        // Check overflow condition
        if (isFull()) {
            System.out.println("Queue is full. Cannot add more patients.");
            return;
        }

        /**
         * Circular movement:
         * If rear is at end, go back to 0
         */
        rear = (rear + 1) % capacity;

        // Insert patient at rear
        queue[rear] = patient;

        // Increase count
        count++;
    }


    /**
     * Removes a patient from the queue (dequeue operation)
     * 
     * Steps:
     * 1. Check if empty
     * 2. Get front element
     * 3. Move front forward (circularly)
     * 4. Decrease count
     */
    public Patient dequeue() {

        // Check underflow condition
        if (isEmpty()) {
            return null;
        }

        // Get patient at front
        Patient removed = queue[front];

        // Optional: clear slot (good practice)
        queue[front] = null;

        /**
         * Move front forward circularly
         */
        front = (front + 1) % capacity;

        // Decrease count
        count--;

        return removed;
    }


    /**
     * Returns the front patient WITHOUT removing them
     */
    public Patient peek() {

        if (isEmpty()) {
            return null;
        }

        return queue[front];
    }


    /**
     * Displays all patients in queue (in correct order)
     */
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("No regular patients waiting.");
            return;
        }

        System.out.println("Regular Patients (FIFO order):");

        /**
         * We cannot just loop from 0 to capacity,
         * because queue is circular.
         * 
         * So we loop 'count' times starting from front.
         */
        for (int i = 0; i < count; i++) {

            // Calculate actual index in circular manner
            int index = (front + i) % capacity;

            System.out.println(queue[index]);
        }
    }


    /**
     * Returns all patients as an array
     * 
     * Used for searching or processing
     */
    public Patient[] getAllPatients() {

        Patient[] patients = new Patient[count];

        for (int i = 0; i < count; i++) {
            int index = (front + i) % capacity;
            patients[i] = queue[index];
        }

        return patients;
    }
}