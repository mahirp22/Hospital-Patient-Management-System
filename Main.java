import java.util.Scanner;

/**
 * Main class
 * 
 * This class contains the main method.
 * It handles:
 * - displaying the menu
 * - taking user input
 * - calling methods from HospitalManagementSystem
 * 
 * Important:
 * This class should only handle user interaction.
 * The actual hospital logic is handled by HospitalManagementSystem.
 */
public class Main {

    /**
     * main method
     * 
     * This is the starting point of the entire Java program.
     */
    public static void main(String[] args) {

        // Scanner object is used to read user input from keyboard
        Scanner input = new Scanner(System.in);

        /**
         * Create the hospital system
         * 
         * Here, both emergency heap and regular queue have capacity 100.
         * You can increase or decrease this later if needed.
         */
        HospitalManagementSystem hospital = new HospitalManagementSystem(100, 100);

        // Variable to store the user's menu choice
        int choice;

        /**
         * do-while loop:
         * The menu will keep repeating until the user chooses 9 (Exit).
         */
        do {
            // Display the main menu
            System.out.println("\n===== Hospital Patient Management System =====");
            System.out.println("1. Add Emergency Patient");
            System.out.println("2. Add Regular Patient");
            System.out.println("3. Serve Next Patient");
            System.out.println("4. View Next Emergency Patient");
            System.out.println("5. View Next Regular Patient");
            System.out.println("6. Display All Waiting Patients");
            System.out.println("7. Search Patient by ID");
            System.out.println("8. Show System Status");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            /**
             * Input validation:
             * If the user enters something that is not an integer,
             * keep asking until they enter a number.
             */
            while (!input.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                input.next(); // discard invalid input
            }

            // Read the user's menu choice
            choice = input.nextInt();

            /**
             * Consume the leftover newline after nextInt()
             * 
             * This is very important when mixing nextInt() and nextLine()
             */
            input.nextLine();

            /**
             * switch statement:
             * Executes a different block depending on the menu choice.
             */
            switch (choice) {

                case 1:
                    // Add emergency patient
                    System.out.print("Enter patient name: ");
                    String eName = input.nextLine();

                    System.out.print("Enter age: ");
                    int eAge = input.nextInt();

                    System.out.print("Enter severity (1-5): ");
                    int severity = input.nextInt();

                    // Consume leftover newline
                    input.nextLine();

                    System.out.print("Enter condition description: ");
                    String eCondition = input.nextLine();

                    hospital.addEmergencyPatient(eName, eAge, severity, eCondition);
                    break;

                case 2:
                    // Add regular patient
                    System.out.print("Enter patient name: ");
                    String rName = input.nextLine();

                    System.out.print("Enter age: ");
                    int rAge = input.nextInt();

                    // Consume leftover newline
                    input.nextLine();

                    System.out.print("Enter condition description: ");
                    String rCondition = input.nextLine();

                    hospital.addRegularPatient(rName, rAge, rCondition);
                    break;

                case 3:
                    // Serve next patient
                    hospital.serveNextPatient();
                    break;

                case 4:
                    // Show next emergency patient without removing
                    hospital.showNextEmergencyPatient();
                    break;

                case 5:
                    // Show next regular patient without removing
                    hospital.showNextRegularPatient();
                    break;

                case 6:
                    // Display all patients currently waiting
                    hospital.displayAllPatients();
                    break;

                case 7:
                    // Search for a patient using ID
                    System.out.print("Enter patient ID to search: ");
                    int id = input.nextInt();

                    // Consume leftover newline
                    input.nextLine();

                    hospital.searchPatientById(id);
                    break;

                case 8:
                    // Show system statistics
                    hospital.systemStatus();
                    break;

                case 9:
                    // Exit program
                    System.out.println("Exiting system.");
                    break;

                default:
                    // Handle invalid menu number
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);

        // Close scanner after loop ends
        input.close();
    }
}