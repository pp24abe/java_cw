import java.util.List;

class Doctor {
    private String name;
    private String specialization;
    private String address;
    private String phoneNumber;
    private List<Appointment> availableAppointments;
    private String treatment;

    public Doctor(String name, String specialization, String address, String phoneNumber, List<Appointment> availableAppointments,String treatment) {
        this.name = name;
        this.specialization = specialization;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.availableAppointments = availableAppointments;
        this.treatment=treatment;
    }

    public Doctor() {

    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Appointment> getAvailableAppointments() {
        return availableAppointments;
    }

    public String getTreatment() {
        return treatment;
    }

    public void showAvailableAppointments() {
        System.out.println("\nDoctor: " + name + " (Specialization: " + specialization + ") , treatementname : "+ treatment );
        for (Appointment a : availableAppointments) {
            System.out.println("📅 " + a.getDate() + " | ⏰ " + a.getTime());
        }
    }
}