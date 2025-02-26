import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class booking extends treatment {

    private List<Doctor> doctors;
    private Map<patient, Appointment> bookedAppointments;

    public booking(List<Doctor> doctors) {
        this.doctors = doctors;
        this.bookedAppointments = new HashMap<>();
    }
    public void searchByspecialization(String specialization) {

        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                doctor.showAvailableAppointments();
                found = true;
            }
            if (!found) {
                System.out.println("❌ No doctors found with specialization: " + specialization);
            }
        }

    }

    public void searchBydocname(String doctor_name) {
        boolean found = false;
        for (Doctor doctor : doctors) {
            if(doctor.getName().equalsIgnoreCase(doctor_name)) {
                doctor.showAvailableAppointments();
                found = true;
            }
            if (!found) {
                System.out.println("❌ No doctor found with name: " + doctor_name);
            }
        }
    }
    public void bookAppointment(patient patient, Doctor doctor, String date, String time) {
        Appointment appointment = new Appointment(date, time);
        bookedAppointments.put(patient, appointment);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println("****************************************");
        System.out.println("\t\tBOOKING RECEIPT");
        System.out.println("****************************************");

        System.out.println("Date & Time:     " + formattedDateTime);
        System.out.println("----------------------------------------");
        System.out.println("Patient Name:    " + patient.get_pat_name());
        System.out.println("Doctor Name:     Dr. " + doctor.getName());
        System.out.println("Specialization:  " + doctor.getSpecialization());
        System.out.println("Appointment Date: " + date);
        System.out.println("Appointment Time: " + time);
        System.out.println("----------------------------------------");

        System.out.println("✅ Appointment successfully booked!");
        System.out.println("****************************************");


        System.out.println(bookedAppointments.get(patient));
    }
}
