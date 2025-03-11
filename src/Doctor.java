import java.util.List;

public class Doctor {


    private String name;
    private String specialization;
    private String address;
    private String phoneNumber;
    private List<Appointment> availableAppointments;



    public Doctor(String name, String specialization, String address, String phoneNumber, List<Appointment> availableAppointments) {
        this.name = name;
        this.specialization = specialization;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.availableAppointments = availableAppointments;
        System.out.println("Available appointments for Dr. " + name + " (" + specialization + "):");
        for ( Appointment appointment : availableAppointments) {
            System.out.println("- " + appointment.getDate() + " at " + appointment.getTime());
        }
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

    public String getDoc_number() {
        return phoneNumber;
    }

    public void setDoc_number(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void showAvailableAppointments() {
        System.out.println("Doctor: " + name + " (Specialization: " + specialization + ")");
        for (Appointment a : availableAppointments) {
            System.out.println("📅 " + a.getDate() + " | ⏰ " + a.getTime());

        }

    }
}
