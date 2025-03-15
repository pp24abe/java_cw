import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public boolean searchByspecialization(String specialization) {
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
        return found;
    }

    public boolean searchBydocname(String doctor_name) {
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
        return found;
    }

    public patient searchBypatname(String patient_id) {
        boolean found = false;
        patient patient1 = null;
        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            patient patient = entry.getKey();
            if (patient.getBiud().equalsIgnoreCase(patient_id)) {
                System.out.println("Appointments found for patient with ID: " + patient.getBiud());
                for (Appointment appointment : entry.getValue()) {
                    System.out.println(appointment);
                }
                found = true;
                patient1 = patient;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ No appointment found for patient with ID: " + patient_id);
        }
        return patient1;
    }

    public String bookingchecking(){
        if (bookedAppointments.isEmpty()) {
            return "empty";
        }
        return "";
    }

    public void showtimetable(String doctor_name)
    {
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

    public boolean viewBookings(String patname ) {
        if (bookedAppointments.isEmpty()) {
            System.out.println("No appointments booked.");
            return false;
        }
        boolean found = false;
        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            patient patient = entry.getKey();
            if (patient.get_pat_name().equalsIgnoreCase(patname)) {

                System.out.println("✅Appointments found for patient with ID: " + patient.getBiud());
                System.out.println("\n****************************************");
                System.out.println("\t\tBOOKED APPOINTMENTS");
                System.out.println("****************************************");
                for (Appointment appointment : entry.getValue()) {
                    System.out.println("👨🏻‍⚕️ Doctor Name:     Dr. " + appointment.getDoctor());
                    System.out.println("🆔 UID:             " + appointment.getUid());
                    System.out.println("🆔 Booking ID:      " + appointment.getBuid());
                    System.out.println("🩺 TREATMENT NAME:  " + appointment.getTreatmentname());
                    System.out.println("🩺Specialization:   " + appointment.getSpecialization());
                    System.out.println("📅Appointment Date: " + appointment.getDate());
                    System.out.println("⏲️Appointment Time: " + appointment.getTime());
                    System.out.println("❓Appointment Status: " + appointment.getStatus());
                    System.out.println("----------------------------------------\n\n");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ No appointment found for patient : " + patname);

        }


        return found;
    }

    public void removeAppointment(patient patient) {
        if (bookedAppointments.containsKey(patient)) {
            bookedAppointments.remove(patient);
            System.out.println("✅ Appointment successfully removed!");
        } else {
            System.out.println("❌ No appointment found for patient with ID: " + patient.get_pat_id());
        }
    }

    public void bookAppointment(patient patient, Doctor doctor, String date, String time,String uid,String doctor_namee,String buid) {
        if (!doctor.getName().equalsIgnoreCase(doctor_namee)) {
            System.out.println("Error: Doctor name does not match.");
            return; // Stop execution if the doctor's name does not match
        }
        treatment treatment= new treatment();
        Appointment appointment = new Appointment(date, time, doctor.getName(), doctor.getSpecialization(), uid,buid,"BOOKED",doctor.getTreatment(),0);


        // Add appointment for the specific patient
        bookedAppointments.computeIfAbsent(patient, k -> new ArrayList<>()).add(appointment);

        // Print confirmation for the current patient only
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        System.out.println("****************************************");
        System.out.println("\t\tBOOKING RECEIPT");
        System.out.println("****************************************");

        System.out.println("📅 DATE & TIME:       " + formattedDateTime);
        System.out.println("----------------------------------------");
        System.out.println("🤵🏻 PATIENT NAME:      " + patient.get_pat_name());
        System.out.println("🆔 UID:               " + uid);
        System.out.println("🆔 Booking ID:        " + buid);
        System.out.println("👨🏻‍⚕️ DOCTOR NAME:       Dr. " + doctor.getName());
        System.out.println("🩺 TREATMENT NAME:    " + appointment.getTreatmentname());
        System.out.println("🩺 SPECIALIZATION:    " + doctor.getSpecialization());
        System.out.println("📅 APPOINTMENT DATE:  " + date);
        System.out.println("⏲️ APPOINTMENT TIME:  " + time);
        System.out.println("❓ Appointment Status:" + appointment.getStatus());
        System.out.println("----------------------------------------");

        System.out.println("✅ APPOINTMENT SUCCESSFULLY BOOKED!");
        System.out.println("****************************************");



    }

    public void changeAppointmentStatus(String BookinID, String status) {

        if (bookedAppointments.isEmpty()) {
            System.out.println("No appointments booked.");
        }
        boolean found = false;
        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            patient patient = entry.getKey();
            if (patient.getBiud().equalsIgnoreCase(BookinID)) {
                for (Appointment appointment : entry.getValue()) {
                    if (appointment.getBuid().equalsIgnoreCase(BookinID)) {
                        if (appointment.getStatus().equalsIgnoreCase("BOOKED")) {
                            appointment.setStatus(status);
                            appointment.changed(0);
                        } else if (appointment.getStatus().equalsIgnoreCase("CANCELLED")) {
                            System.out.println("Your appointment has been cancelled, and you cannot change it.");
                            appointment.changed(1);
                            break;
                        } else if (appointment.getStatus().equalsIgnoreCase("ATTENDED")) {
                            System.out.println("Your appointment has already been marked as attended, and you cannot change it.");
                            appointment.changed(1);
                            break;
                        }
                    }
                }
                if (!patient.getBiud().equals(BookinID)) {
                    System.out.println("Error: BookingID does not match.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ No appointment found for patient with BookingID: " + BookinID);
        }



    }
    public boolean viewBooking(String bookingId ) {
        if (bookedAppointments.isEmpty()) {
            System.out.println("No appointments booked.");
            return false;
        }
        boolean found = false;
        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            patient patient = entry.getKey();
            if (patient.getBiud().equalsIgnoreCase(bookingId)) {


                for (Appointment appointment : entry.getValue()) {

                    if(appointment.getBuid().equalsIgnoreCase(bookingId)&& appointment.getchanged()==2) {
                        System.out.println("\n✅ APPOINTMENT STATUS SUCCESSFULLY SET TO "+appointment.getStatus()+"!");
                        System.out.println("\n****************************************");
                        System.out.println("\t\tCHANGE STATUS RECEIPT");
                        System.out.println("****************************************");
                        System.out.println("👨🏻‍⚕️ Doctor Name:     Dr. " + appointment.getDoctor());
                        System.out.println("🆔 UID:             " + appointment.getUid());
                        System.out.println("🆔 Booking ID:      " + appointment.getBuid());
                        System.out.println("🩺 TREATMENT NAME:  " + appointment.getTreatmentname());
                        System.out.println("🩺Specialization:   " + appointment.getSpecialization());
                        System.out.println("📅Appointment Date: " + appointment.getDate());
                        System.out.println("⏲️Appointment Time: " + appointment.getTime());
                        System.out.println("❓Appointment Status: " + appointment.getStatus());
                        System.out.println("----------------------------------------\n\n");
                    }


                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ No appointment found for patient with ID : " + bookingId);

        }


        return found;
    }

    public void generateClinicReport() {
        if (bookedAppointments.isEmpty()) {
            System.out.println("\n❌ No appointments available for the report.");
            return;
        }

        Map<Doctor, List<Appointment>> doctorAppointments = new HashMap<>();

        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            patient pat = entry.getKey();
            for (Appointment appointment : entry.getValue()) {
                Doctor doctor = findDoctorByName(appointment.getDoctor());
                if (doctor != null) {
                    doctorAppointments.computeIfAbsent(doctor, k -> new ArrayList<>()).add(appointment);
                }
            }
        }

        System.out.println("\n============================================================================================================");
        System.out.println("                               🏥 CLINIC REPORT - APPOINTMENTS LIST");
        System.out.println("============================================================================================================");

        System.out.printf("| %-20s | %-15s | %-25s \t\t \t\t\t\t\t| %-9s|\n", "Doctor", "Patient", "Date & Time", "Status");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (Map.Entry<Doctor, List<Appointment>> entry : doctorAppointments.entrySet()) {
            Doctor doctor = entry.getKey();
            for (Appointment appointment : entry.getValue()) {
                patient pat = findPatientByAppointment(appointment);
                System.out.printf("| %-20s | %-15s | %-25s \t\t\t| %-9s| \n",
                        "Dr. " + doctor.getName(),
                        pat != null ? pat.get_pat_name() : "Unknown",
                        appointment.getDate() + " at " + appointment.getTime(),
                        appointment.getStatus());
            }
        }

        List<Map.Entry<Doctor, List<Appointment>>> sortedDoctors = doctorAppointments.entrySet().stream()
                .sorted((d1, d2) -> Long.compare(
                        d2.getValue().stream().filter(a -> a.getStatus().equalsIgnoreCase("ATTENDED")).count(),
                        d1.getValue().stream().filter(a -> a.getStatus().equalsIgnoreCase("ATTENDED")).count()
                ))
                .collect(Collectors.toList());

        System.out.println("\n============================================================================================================");
        System.out.println("                                                            🏆 PHYSIOTHERAPISTS BY ATTENDED APPOINTMENTS");
        System.out.println("============================================================================================================");
        System.out.printf("                                                             | %-20s | %-12s |\n", "Doctor", "Attended Count");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (Map.Entry<Doctor, List<Appointment>> entry : sortedDoctors) {
            long attendedCount = entry.getValue().stream()
                    .filter(a -> a.getStatus().equalsIgnoreCase("ATTENDED"))
                    .count();
            System.out.printf("                                                             | %-20s | \t\t%-8d |\n", "Dr. " + entry.getKey().getName(), attendedCount);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }

    // Helper methods remain unchanged
    private Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                return doctor;
            }
        }
        return null;
    }

    private patient findPatientByAppointment(Appointment appointment) {
        for (Map.Entry<patient, List<Appointment>> entry : bookedAppointments.entrySet()) {
            if (entry.getValue().contains(appointment)) {
                return entry.getKey();
            }
        }
        return null;
    }

}

