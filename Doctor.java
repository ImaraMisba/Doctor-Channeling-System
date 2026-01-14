public class Doctor {

    private int doctorId;
    private String name;
    private String specialization;
    private String[] timeSlots;
    private double consultationFee;

    public Doctor(int doctorId, String name, String specialization,
                  String[] timeSlots, double consultationFee) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.timeSlots = timeSlots;
        this.consultationFee = consultationFee;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String[] getTimeSlots() {
        return timeSlots;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void displayDoctor() {
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
        System.out.print("Time Slots: ");
        for (String slot : timeSlots) {
            System.out.print(slot + " ");
        }
        System.out.println();
        System.out.println("Consultation Fee: Rs." + consultationFee);
        System.out.println("-----------------------------");
    }
}
