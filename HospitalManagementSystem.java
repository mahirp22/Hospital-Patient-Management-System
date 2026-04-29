/**
 * HospitalManagementSystem class
 * 
 * This class acts as the main controller of the hospital project.
 * It connects:
 * - MinHeap for emergency patients
 * - CircularQueue for regular patients
 * 
 * Responsibilities:
 * - add patients
 * - serve patients
 * - display system information
 * - search for patients
 */
public class HospitalManagementSystem {

    // Stores emergency patients using priority ordering
    private MinHeap emergencyPatients;

    // Stores regular patients using FIFO ordering
    private CircularQueue regularPatients;

    // Used to give each patient a unique ID
    private int nextPatientId;

    // Statistics: total number of emergency patients served
    private int totalServedEmergency;

    // Statistics: total number of regular patients served
    private int totalServedRegular;


    /**
     * Constructor
     * 
     * Creates the hospital system with capacities for:
     * - emergency patient heap
     * - regular patient queue
     */
    public HospitalManagementSystem(int emergencyCapacity, int regularCapacity) {
        emergencyPatients = new MinHeap(emergencyCapacity);
        regularPatients = new CircularQueue(regularCapacity);

        // Start assigning patient IDs from 1
        nextPatientId = 1;

        // Initially no patients have been served
        totalServedEmergency = 0;
        totalServedRegular = 0;
    }


    /**
     * Adds a new emergency patient
     * 
     * Emergency patients are inserted into the MinHeap
     * based on severity level.
     */
    public void addEmergencyPatient(String name, int age, int severity, String condition) {

        // Validate severity range
        if (severity < 1 || severity > 5) {
            System.out.println("Severity must be between 1 and 5.");
            return;
        }

        // Create new Patient object
        Patient patient = new Patient(
            nextPatientId,
            name,
            age,
            severity,
            "Emergency",
            condition
        );

        // Insert into emergency heap
        emergencyPatients.insert(patient);

        // Prepare next unique ID
        nextPatientId++;

        System.out.println("Emergency patient added successfully.");
    }


    /**
     * Adds a new regular patient
     * 
     * Regular patients are placed into the CircularQueue.
     * 
     * Since severity is not important for regular patients,
     * we simply assign severity 5.
     */
    public void addRegularPatient(String name, int age, String condition) {

        // Create new Patient object
        Patient patient = new Patient(
            nextPatientId,
            name,
            age,
            5,
            "Regular",
            condition
        );

        // Enqueue into regular patient queue
        regularPatients.enqueue(patient);

        // Prepare next unique ID
        nextPatientId++;

        System.out.println("Regular patient added successfully.");
    }


    /**
     * Serves the next patient
     * 
     * RULE:
     * 1. If any emergency patients exist, serve the highest-priority one first
     * 2. Otherwise serve the next regular patient
     * 3. If no patients exist, display message
     */
    public void serveNextPatient() {
        Patient served;

        // Emergency patients always have priority
        if (!emergencyPatients.isEmpty()) {
            served = emergencyPatients.removeMin();
            totalServedEmergency++;

            System.out.println("Serving emergency patient:");
            System.out.println(served);
        }
        // If no emergency patients, serve regular patient
        else if (!regularPatients.isEmpty()) {
            served = regularPatients.dequeue();
            totalServedRegular++;

            System.out.println("Serving regular patient:");
            System.out.println(served);
        }
        // No patients at all
        else {
            System.out.println("No patients waiting.");
        }
    }


    /**
     * Shows the next emergency patient without removing them
     */
    public void showNextEmergencyPatient() {
        Patient patient = emergencyPatients.peekMin();

        if (patient == null) {
            System.out.println("No emergency patients waiting.");
        } else {
            System.out.println("Next emergency patient:");
            System.out.println(patient);
        }
    }


    /**
     * Shows the next regular patient without removing them
     */
    public void showNextRegularPatient() {
        Patient patient = regularPatients.peek();

        if (patient == null) {
            System.out.println("No regular patients waiting.");
        } else {
            System.out.println("Next regular patient:");
            System.out.println(patient);
        }
    }


    /**
     * Displays all waiting patients
     * 
     * Includes:
     * - emergency patients
     * - regular patients
     */
    public void displayAllPatients() {
        System.out.println();
        emergencyPatients.displayHeap();

        System.out.println();
        regularPatients.displayQueue();
    }


    /**
     * Displays system statistics
     */
    public void systemStatus() {
        System.out.println("Emergency patients waiting: " + emergencyPatients.size());
        System.out.println("Regular patients waiting: " + regularPatients.size());
        System.out.println("Total emergency patients served: " + totalServedEmergency);
        System.out.println("Total regular patients served: " + totalServedRegular);
    }


    /**
     * Searches for a patient by ID
     * 
     * Since patients may be in either structure:
     * 1. Search emergency heap
     * 2. Search regular queue
     */
    public void searchPatientById(int id) {

        // Search emergency patients first
        Patient[] emergencyList = emergencyPatients.getAllPatients();
        for (int i = 0; i < emergencyList.length; i++) {
            if (emergencyList[i].getPatientId() == id) {
                System.out.println("Patient found in emergency list:");
                System.out.println(emergencyList[i]);
                return;
            }
        }

        // Search regular patients next
        Patient[] regularList = regularPatients.getAllPatients();
        for (int i = 0; i < regularList.length; i++) {
            if (regularList[i].getPatientId() == id) {
                System.out.println("Patient found in regular list:");
                System.out.println(regularList[i]);
                return;
            }
        }

        // If not found in either structure
        System.out.println("Patient with ID " + id + " not found.");
    }
}