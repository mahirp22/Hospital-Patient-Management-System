/**
 * Patient class represents a single patient in the hospital system.
 * 
 * This class is used by both:
 * - MinHeap (for emergency patients)
 * - CircularQueue (for regular patients)
 * 
 * It stores all relevant patient information and provides access methods.
 */
public class Patient {

    // Unique identifier for each patient (used for searching)
    private int patientId;

    // Patient's name
    private String name;

    // Patient's age
    private int age;

    /**
     * Severity level (ONLY relevant for emergency patients)
     * 
     * Lower value = higher priority
     * Example:
     * 1 = critical (highest priority)
     * 5 = minor (lowest priority)
     */
    private int severityLevel;

    /**
     * Arrival type indicates how the patient entered the system
     * "Emergency" → goes into MinHeap
     * "Regular" → goes into CircularQueue
     */
    private String arrivalType;

    // Short description of the patient's condition
    private String conditionDescription;


    /**
     * Constructor
     * 
     * This is called whenever a new Patient object is created.
     * It initializes all attributes.
     */
    public Patient(int patientId, String name, int age, int severityLevel,
                   String arrivalType, String conditionDescription) {

        // Assign values passed as parameters to the object's fields
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.severityLevel = severityLevel;
        this.arrivalType = arrivalType;
        this.conditionDescription = conditionDescription;
    }


    /**
     * Getter for patient ID
     * 
     * Used when searching for a patient
     */
    public int getPatientId() {
        return patientId;
    }


    /**
     * Getter for patient name
     */
    public String getName() {
        return name;
    }


    /**
     * Getter for age
     */
    public int getAge() {
        return age;
    }


    /**
     * Getter for severity level
     * 
     * IMPORTANT:
     * This is used by the MinHeap to compare patients
     * and determine priority ordering.
     */
    public int getSeverityLevel() {
        return severityLevel;
    }


    /**
     * Getter for arrival type (Emergency or Regular)
     */
    public String getArrivalType() {
        return arrivalType;
    }


    /**
     * Getter for condition description
     */
    public String getConditionDescription() {
        return conditionDescription;
    }


    /**
     * toString method
     * 
     * This is automatically called when:
     * - printing the object (System.out.println(patient))
     * 
     * It converts the object into a readable string.
     */
    public String toString() {
        return "ID: " + patientId +
               ", Name: " + name +
               ", Age: " + age +
               ", Severity: " + severityLevel +
               ", Type: " + arrivalType +
               ", Condition: " + conditionDescription;
    }
}