public class Appointment {

    private Patient patient;
    private Doctor doctor;
    private String timeSlot;

    public Appointment(Patient patient, Doctor doctor, String timeSlot) {
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
    }


    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void displayAppointment() {
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Time Slot: " + timeSlot);
        System.out.println("-----------------------------");
    }
}
