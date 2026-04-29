# Hospital Patient Management System Using Java Data Structures

This project applies **core data structures concepts**, specifically **priority queues (MinHeap) and circular queues**, to simulate a real-world hospital patient management system. The system efficiently handles patient triaging by prioritizing emergency cases while maintaining fair scheduling for regular patients.

Using Java, the application models how hospitals process patients based on urgency and arrival order, demonstrating how data structures can be used to solve practical problems involving prioritization and scheduling.  
This project showcases a complete workflow, from system design and implementation to performance testing and time complexity analysis.

The project was built as part of a data structures course, with a focus on understanding how different data structures behave in real-world scenarios rather than just theoretical use.

## 📦 Technologies

- Java  
- Object-Oriented Programming (OOP)  
- Data Structures (MinHeap, Circular Queue)  
- System.nanoTime() (Performance Testing)

## 💡 Features

Here’s what this hospital management system includes:

### 🏥 Patient Management System
- Simulates real-world hospital patient intake and processing  
- Differentiates between emergency and regular patients  
- Ensures efficient and fair handling of all patients  

### ⚡ Priority-Based Emergency Handling (MinHeap)
- Emergency patients are prioritized based on severity level  
- Lower severity value = higher priority  
- Efficient insertion and removal operations using heap structure  

### 🔄 FIFO Scheduling for Regular Patients (Circular Queue)
- Regular patients are processed in order of arrival  
- Ensures fairness using First-In-First-Out (FIFO) logic  
- Constant-time enqueue and dequeue operations  

### 📊 Performance Testing & Analysis
- Measures execution time for all major operations  
- Tests system performance across different input sizes  
- Compares real results with theoretical time complexity  

## 📁 Project Structure

The system is organized into multiple classes:

- **Patient** → Stores patient information (ID, name, age, severity, etc.)  
- **MinHeap** → Manages emergency patients based on priority  
- **CircularQueue** → Manages regular patients using FIFO  
- **HospitalManagementSystem** → Coordinates overall system logic  
- **PerformanceTest** → Handles runtime testing and analysis  
- **Main** → Entry point of the application  

---

## 🚦 Running the Project Locally

To run this project on your local machine:

1. Clone the repository to your computer.
2. Navigate to the project directory.
3. Compile the Java files:
   ```bash
   javac *.java
