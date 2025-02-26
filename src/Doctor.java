import java.util.List;
import java.util.Scanner;

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
