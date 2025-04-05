import java.util.*;

// Class representing a patient
class Patient {
    int id;
    String name;
    int age;
    String contact;
    LinkedList<String> medicalHistory;
    
    public Patient(int id, String name, int age, String contact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.medicalHistory = new LinkedList<>();
    }
    
    // Method to add a record to the patient's medical history
    public void addMedicalRecord(String record) {
        medicalHistory.add(record);
    }
    
    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + 
               ", Contact: " + contact + ", Medical History: " + medicalHistory;
    }
}

// Class to manage patient records using a singly linked list
class PatientRecords {
    // Node class for the linked list
    private class Node {
        Patient patient;
        Node next;
        
        public Node(Patient patient) {
            this.patient = patient;
        }
    }
    
    private Node head;
    
    // Add a new patient record
    public void addPatient(Patient p) {
        Node newNode = new Node(p);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    // Update a patient's record by ID
    public void updatePatient(int id, Patient updatedPatient) {
        Node current = head;
        while (current != null) {
            if (current.patient.id == id) {
                current.patient = updatedPatient;
                return;
            }
            current = current.next;
        }
        System.out.println("Patient with ID " + id + " not found.");
    }
    
    // Remove a patient record by ID
    public void removePatient(int id) {
        if (head == null)
            return;
        if (head.patient.id == id) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.patient.id == id) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
        System.out.println("Patient with ID " + id + " not found.");
    }
    
    // Display all patient records
    public void displayPatients() {
        Node current = head;
        while (current != null) {
            System.out.println(current.patient);
            current = current.next;
        }
    }
}

// Class representing an emergency patient with a priority value
class EmergencyPatient {
    Patient patient;
    int priority; // Lower number indicates higher priority (more critical)
    
    public EmergencyPatient(Patient patient, int priority) {
        this.patient = patient;
        this.priority = priority;
    }
}

// Class to manage the emergency queue using a PriorityQueue
class EmergencyQueue {
    PriorityQueue<EmergencyPatient> queue;
    
    public EmergencyQueue() {
        // Comparator to sort patients by their priority value
        queue = new PriorityQueue<>(new Comparator<EmergencyPatient>() {
            @Override
            public int compare(EmergencyPatient ep1, EmergencyPatient ep2) {
                return Integer.compare(ep1.priority, ep2.priority);
            }
        });
    }
    
    // Enqueue a patient into the emergency queue
    public void enqueue(EmergencyPatient ep) {
        queue.add(ep);
    }
    
    // Dequeue and return the highest priority patient
    public EmergencyPatient dequeue() {
        return queue.poll();
    }
    
    // Display the current emergency queue
    public void displayQueue() {
        for (EmergencyPatient ep : queue) {
            System.out.println("Patient: " + ep.patient.name + " with priority: " + ep.priority);
        }
    }
}

// Class to manage a patient's treatment history using a stack
class TreatmentHistory {
    Stack<String> treatments;
    
    public TreatmentHistory() {
        treatments = new Stack<>();
    }
    
    // Add a new treatment record
    public void addTreatment(String treatment) {
        treatments.push(treatment);
    }
    
    // Retrieve the last treatment performed
    public String lastTreatment() {
        if (!treatments.isEmpty())
            return treatments.peek();
        else
            return "No treatments recorded.";
    }
    
    // Display the full treatment history
    public void displayHistory() {
        System.out.println("Treatment History:");
        for (String t : treatments) {
            System.out.println(t);
        }
    }
}

// Class representing a doctor
class Doctor {
    int id;
    String name;
    String department;
    String schedule;
    
    public Doctor(int id, String name, String department, String schedule) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.schedule = schedule;
    }
    
    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + 
               ", Department: " + department + ", Schedule: " + schedule;
    }
}

// Class to manage doctor assignments using a hash table (HashMap)
class DoctorAssignments {
    HashMap<Integer, Doctor> doctors;
    
    public DoctorAssignments() {
        doctors = new HashMap<>();
    }
    
    // Add a new doctor
    public void addDoctor(Doctor d) {
        doctors.put(d.id, d);
    }
    
    // Retrieve a doctor's details by ID
    public Doctor getDoctor(int id) {
        return doctors.get(id);
    }
    
    // Display all assigned doctors
    public void displayDoctors() {
        System.out.println("Doctor Assignments:");
        for (Doctor d : doctors.values()) {
            System.out.println(d);
        }
    }
}



// Main class to test the Hospital Management System
public class HospitalManagementSystem {
    public static void main(String[] args) {
        // Step 1: Test Patient Records
        System.out.println("=== Test Patient Records ===");
        PatientRecords records = new PatientRecords();
        Patient patient1 = new Patient(101, "Ahmed", 25, "45466464664");
        Patient patient2 = new Patient(102, "Ali", 40, "4564646400");
        records.addPatient(patient1);
        records.addPatient(patient2);
        records.displayPatients();
        
        // Step 2: Test Emergency Queue
        System.out.println("\n=== Test Emergency Queue ===");
        EmergencyQueue emergencyQueue = new EmergencyQueue();
        // Enqueue patients with priorities (Ahmed: less critical with priority 3, Ali: more critical with priority 1)
        emergencyQueue.enqueue(new EmergencyPatient(patient1, 3));
        emergencyQueue.enqueue(new EmergencyPatient(patient2, 1));
        emergencyQueue.displayQueue();
        EmergencyPatient treatedPatient = emergencyQueue.dequeue();
        System.out.println("Treating patient: " + treatedPatient.patient.name);
        
        // Step 3: Test Treatment History
        System.out.println("\n=== Test Treatment History ===");
        TreatmentHistory history = new TreatmentHistory();
        history.addTreatment("Surgery on 2025-02-01");
        history.addTreatment("Prescribed Antibiotics");
        history.addTreatment("Annual Checkup");
        history.displayHistory();
        System.out.println("Last treatment performed: " + history.lastTreatment());
        
        // Step 4: Test Doctor Assignments
        System.out.println("\n=== Test Doctor Assignments ===");
        DoctorAssignments assignments = new DoctorAssignments();
        Doctor doctor1 = new Doctor(105, "Dr.Turki", "Emergency", "8:00AM-5:30PM");
        Doctor doctor2 = new Doctor(209, "Dr.Abdullah", "pediatrics", "9:20AM-9:45PM");
        assignments.addDoctor(doctor1);
        assignments.addDoctor(doctor2);
        assignments.displayDoctors();
    }
}

