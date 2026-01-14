import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class DoctorChannelingSystem {

    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static LinkedList<Appointment> appointments = new LinkedList<>();
    static Queue<Patient> waitingQueue = new LinkedList<>();
    static Stack<Patient> recentPatients = new Stack<>();

    static int doctorIdCounter = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("-------------------------");
            System.out.println("CLINIC APPOINTMENT SYSTEM");
            System.out.println("-------------------------");
            System.out.println("1  - Initialize");
            System.out.println("2  - Register patient");
            System.out.println("3  - Display patients");
            System.out.println("4  - Register doctor");
            System.out.println("5  - Display doctors");
            System.out.println("6  - Search doctor");
            System.out.println("7  - Make appointment");
            System.out.println("8  - Display appointments");
            System.out.println("9  - Cancel appointment");
            System.out.println("10 - Display recent patients");
            System.out.println("11 - Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    initializeSystem();
                    break;

                case 2:
                    registerPatient(sc);
                    break;

                case 3:
                    displayPatients();
                    break;

                case 4:
                    registerDoctor(sc);
                    break;

                case 5:
                    displayDoctors();
                    break;

                case 6:
                    searchDoctor(sc);
                    break;

                case 7:
                    makeAppointment(sc);
                    break;

                case 8:
                    displayAppointments();
                    break;

                case 9:
                    cancelAppointment(sc);
                    break;

                case 10:
                    displayRecentPatients();
                    break;

                case 11:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 11);

        sc.close();
    }

    //Initialize
    public static void initializeSystem() {

        patients.clear();
        doctors.clear();
        appointments.clear();
        waitingQueue.clear();
        recentPatients.clear();

        System.out.println("System initialized successfully.");
    }

    // Regiister patient
    public static void registerPatient(Scanner sc) {

        sc.nextLine();

        System.out.print("Enter patient name: ");
        String name = sc.nextLine();

        System.out.print("Enter mobile number: ");
        String mobile = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter medical history: ");
        String history = sc.nextLine();

        Patient patient = new Patient(name, mobile, email, city, age, history);
        patients.add(patient);
        recentPatients.push(patient);

        System.out.println("Patient registered successfully!");
    }

    //Display patient
    public static void displayPatients() {

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        for (Patient p : patients) {
            p.displayPatient();
        }
    }

    //register doctor
    public static void registerDoctor(Scanner sc) {

        sc.nextLine();

        int id = doctorIdCounter++;

        System.out.print("Enter doctor name: ");
        String name = sc.nextLine();

        System.out.print("Enter specialization: ");
        String spec = sc.nextLine();

        System.out.print("Enter number of time slots: ");
        int count = sc.nextInt();
        sc.nextLine();

        String[] slots = new String[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Enter slot " + (i + 1) + ": ");
            slots[i] = sc.nextLine();
        }

        System.out.print("Enter consultation fee: ");
        double fee = sc.nextDouble();

        doctors.add(new Doctor(id, name, spec, slots, fee));
        System.out.println("Doctor registered successfully!");
        System.out.println("Generated Doctor ID: " + id);
    }

    // Display doctor
    public static void displayDoctors() {

        if (doctors.isEmpty()) {
            System.out.println("No doctors registered.");
            return;
        }

        for (Doctor d : doctors) {
            d.displayDoctor();
        }
    }

    // search doctor
    public static void searchDoctor(Scanner sc) {

        sc.nextLine();
        System.out.print("Enter specialization to search: ");
        String spec = sc.nextLine();

        boolean found = false;

        for (Doctor d : doctors) {
            if (d.getSpecialization().equalsIgnoreCase(spec)) {
                d.displayDoctor();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No doctor found.");
        }
    }

    // make appointment
    public static void makeAppointment(Scanner sc) {

        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Register patients and doctors first.");
            return;
        }

        System.out.println("Select Patient:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println(i + " - " + patients.get(i).getName());
        }

        int p = sc.nextInt();
        if (p < 0 || p >= patients.size()) {
            System.out.println("Invalid patient selection.");
            return;
        }

        System.out.println("Select Doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor dObj = doctors.get(i);
			System.out.println(i + " - ID: " + dObj.getDoctorId()
			    + " | Name: " + dObj.getName()
				+ " | Specialization: " + dObj.getSpecialization()); 
        }

        int d = sc.nextInt();
        if (d < 0 || d >= doctors.size()) {
            System.out.println("Invalid doctor selection.");
            return;
        }

        sc.nextLine();
        System.out.print("Enter time slot: ");
        String slot = sc.nextLine();

        Patient patient = patients.get(p);
        Doctor doctor = doctors.get(d);

        boolean slotExists = false;
        for (String s : doctor.getTimeSlots()) {
            if (s.equalsIgnoreCase(slot)) {
                slotExists = true;
                break;
            }
        }

        if (!slotExists) {
            System.out.println("No time slot for selected doctor.");
            return;
        }

        for (Appointment a : appointments) {
            if (a.getDoctor() == doctor &&
                a.getTimeSlot().equalsIgnoreCase(slot)) {

                waitingQueue.add(patient);
                System.out.println("Slot already booked. Added to waiting queue.");
                return;
            }
        }

        appointments.add(new Appointment(patient, doctor, slot));
        System.out.println("Appointment booked successfully!");
    }

    // Display appointments
    public static void displayAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (Appointment a : appointments) {
            a.displayAppointment();
        }
    }

    //cancel appointment
    public static void cancelAppointment(Scanner sc) {

        if (appointments.isEmpty()) {
            System.out.println("No appointments to cancel.");
            return;
        }

        for (int i = 0; i < appointments.size(); i++) {
            System.out.print(i + " - ");
            appointments.get(i).displayAppointment();
        }

        int index = sc.nextInt();
        if (index < 0 || index >= appointments.size()) {
            System.out.println("Invalid appointment number.");
            return;
        }

        Appointment removed = appointments.remove(index);
        System.out.println("Appointment cancelled.");

        if (!waitingQueue.isEmpty()) {
            Patient next = waitingQueue.poll();
            appointments.add(
                new Appointment(next, removed.getDoctor(), removed.getTimeSlot())
            );
            System.out.println("Reassigned to waiting patient.");
        }
    }

    // display recent patients
    public static void displayRecentPatients() {

        if (recentPatients.isEmpty()) {
            System.out.println("No recent patients.");
            return;
        }

        System.out.println("--- Recent Patients (LIFO) ---");
        for (int i = recentPatients.size() - 1; i >= 0; i--) {
            recentPatients.get(i).displayPatient();
        }
    }
}
