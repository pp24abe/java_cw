import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class booking extends treatment {

    private List<Doctor> doctors;
    private HashMap<patient, List<Appointment>> bookedAppointments;

    public booking(List<Doctor> doctors) {
        this.doctors = doctors;
        this.bookedAppointments = new HashMap<>();
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void searchByspecialization(String specialization) {
        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                doctor.showAvailableAppointments();
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ No doctors found with specialization: " + specialization);
        }
    }

    public void searchBydocname(String doctor_name) {
        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(doctor_name)) {
                doctor.showAvailableAppointments();
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ No doctor found with name: " + doctor_name);
        }
    }

    public patient searchBypatname(String patient_name) {
        boolean found = false;
        patient patient1 = null; // Initialize patient1
        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            patient patient = entry.getKey();
            if (patient.get_pat_name().equalsIgnoreCase(patient_name)) {
                System.out.println("Appointments found for patient: " + patient_name);
                for (Appointment appointment : entry.getValue()) {
                    System.out.println(appointment);
                }
                found = true;
                patient1 = patient; // Assign patient1 before breaking the loop
                break; // Exit the loop once the patient is found
            }
        }
        if (!found) {
            System.out.println("❌ No appointment found for patient: " + patient_name);
        }
        return patient1;
    }

    public void viewBookings() {
        if (bookedAppointments.isEmpty()) {
            System.out.println("No appointments booked.");
            return;
        }

        System.out.println("****************************************");
        System.out.println("\t\tBOOKED APPOINTMENTS");
        System.out.println("****************************************");

        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            patient pat = entry.getKey();
            List<Appointment> appointments = entry.getValue();

            System.out.println("🤵🏻 Patient Name: " + pat.get_pat_name());
            System.out.println("----------------------------------------");

            for (Appointment appointment : appointments) {
                System.out.println("👨🏻‍⚕️ Doctor Name:     Dr. " + appointment.getDoctor());
                System.out.println("🩺Specialization:  " + appointment.getSpecialization());
                System.out.println("📅Appointment Date: " + appointment.getDate());
                System.out.println("⏲️Appointment Time: " + appointment.getTime());
                System.out.println("----------------------------------------\n\n");

            }
        }
    }

    public void removeAppointment(patient patient) {
        if (bookedAppointments.containsKey(patient)) {
            bookedAppointments.remove(patient);
            System.out.println("✅ Appointment successfully removed!");
        } else {
            System.out.println("❌ No appointment found for patient: " + patient.get_pat_name());
        }
    }

    public void bookAppointment(patient patient, Doctor doctor, String date, String time) {
        Appointment appointment = new Appointment(date, time, doctor.getName(), doctor.getSpecialization());

        // Add appointment for the specific patient
        bookedAppointments.computeIfAbsent(patient, k -> new ArrayList<>()).add(appointment);

        // Print confirmation for the current patient only
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        System.out.println("****************************************");
        System.out.println("\t\tBOOKING RECEIPT");
        System.out.println("****************************************");

        System.out.println("📅Date & Time:     " + formattedDateTime);
        System.out.println("----------------------------------------");
        System.out.println("🤵🏻Patient Name:    " + patient.get_pat_name());
        System.out.println("👨🏻‍⚕️Doctor Name:     Dr. " + doctor.getName());
        System.out.println("🩺Specialization:  " + doctor.getSpecialization());
        System.out.println("📅Appointment Date: " + date);
        System.out.println("⏲️Appointment Time: " + time);
        System.out.println("----------------------------------------");

        System.out.println("✅ Appointment successfully booked!");
        System.out.println("****************************************");

        System.out.println("Current list of booked patients:");
        for (patient p : bookedAppointments.keySet()) {
            System.out.println("- " + p.get_pat_name());
        }
    }

}