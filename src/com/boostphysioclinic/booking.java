package com.boostphysioclinic;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class booking {
    private List<Doctor> doctors;
    private Map<String, Appointment> bookedAppointments;
    private Map<String, List<Appointment>> patientAppointments;
    private List<patient> registeredPatients;

    public booking(List<Doctor> doctors) {
        this.doctors = doctors;
        this.bookedAppointments = new HashMap<>();
        this.patientAppointments = new HashMap<>();
        this.registeredPatients = new ArrayList<>();
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

    public boolean searchBydocname(String name) {
        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ No doctor found with name: " + name);
        }
        return found;
    }

    public void showtimetable(String doctorName) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(doctorName)) {
                System.out.println("\nAvailable Appointments for Dr. " + doctor.getName() + ":");
                List<Appointment> availableAppointments = doctor.getAvailableAppointments();

                Map<String, Boolean> weekHasAvailableSlot = new HashMap<>();

                for (Appointment appointment : availableAppointments) {
                    String appointmentKey = doctorName + "_" + appointment.getDate() + "_" + appointment.getTime();
                    String week = appointment.getDate().split(":")[0].trim();

                    if (!bookedAppointments.containsKey(appointmentKey)) {
                        weekHasAvailableSlot.put(week, true);
                    } else if (!weekHasAvailableSlot.containsKey(week)) {
                        weekHasAvailableSlot.put(week, false);
                    }
                }

                int counter = 1;
                for (int i = 0; i < availableAppointments.size(); i++) {
                    Appointment appointment = availableAppointments.get(i);
                    String week = appointment.getDate().split(":")[0].trim();
                    String appointmentKey = doctorName + "_" + appointment.getDate() + "_" + appointment.getTime();

                    if (weekHasAvailableSlot.get(week) && !bookedAppointments.containsKey(appointmentKey)) {
                        System.out.println((counter) + ". 📅 " + appointment.getDate() + " | ⏰ " + appointment.getTime());
                        counter++;
                    }
                }

                if (counter == 1) {
                    System.out.println("❌ No available appointments found for Dr. " + doctorName);
                }

                return;
            }
        }
        System.out.println("❌ Doctor not found.");
    }

    public void bookAppointment(patient patient, Doctor doctor, String date, String time, int pid, String doctorName, String buid) {
        String appointmentKey = doctorName + "_" + date + "_" + time;
        if (bookedAppointments.containsKey(appointmentKey)) {
            System.out.println("❌ This appointment is already booked. Please select another time slot.");
            return;
        }
        if (hasOverlappingAppointment(patient, date, time)) {
            System.out.println("❌ You already have an appointment scheduled during this time with another doctor.");
            return;
        }

        Appointment newAppointment = new Appointment(date, time, doctorName, doctor.getSpecialization(),
                pid, buid, "BOOKED", doctor.getTreatment(), 0);

        bookedAppointments.put(appointmentKey, newAppointment);

        String patientKey = patient.get_pat_name() + "_" + patient.get_pat_id();
        if (!patientAppointments.containsKey(patientKey)) {
            patientAppointments.put(patientKey, new ArrayList<>());
        }
        patientAppointments.get(patientKey).add(newAppointment);

        if (!isPatientRegistered(patient.get_pat_name())) {
            registeredPatients.add(patient);
        }
        Appointment appointment = new Appointment(date, time, doctor.getName(), doctor.getSpecialization(), pid,buid,"BOOKED",doctor.getTreatment(),0);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        System.out.println("****************************************");
        System.out.println("\t\tBOOKING RECEIPT");
        System.out.println("****************************************");

        System.out.println("📅 DATE & TIME:       " + formattedDateTime);
        System.out.println("----------------------------------------");
        System.out.println("🤵🏻 PATIENT NAME:      " + patient.get_pat_name());
        System.out.println("🆔 UID:               " + pid);
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

    public void bookAppointmenthardcode(patient patient, Doctor doctor, String date, String time, int pid, String doctorName, String buid) {
        String appointmentKey = doctorName + "_" + date + "_" + time;
        Appointment newAppointment = new Appointment(date, time, doctorName, doctor.getSpecialization(),
                pid, buid, "BOOKED", doctor.getTreatment(), 0);

        bookedAppointments.put(appointmentKey, newAppointment);
        String patientKey = patient.get_pat_name() + "_" + patient.get_pat_id();
        if (!patientAppointments.containsKey(patientKey)) {
            patientAppointments.put(patientKey, new ArrayList<>());
        }
        patientAppointments.get(patientKey).add(newAppointment);

        if (!isPatientRegistered(patient.get_pat_name())) {
            registeredPatients.add(patient);
        }
    }
    private boolean hasOverlappingAppointment(patient patient, String date, String timeRange) {
        String patientKey = patient.get_pat_name() + "_" + patient.get_pat_id();
        if (!patientAppointments.containsKey(patientKey)) {
            return false;
        }
        String[] newTimeParts = timeRange.split("-");
        int newStartTime = convertTimeToMinutes(newTimeParts[0]);
        int newEndTime = convertTimeToMinutes(newTimeParts[1]);

        for (Appointment existing : patientAppointments.get(patientKey)) {

            if (existing.getDate().equals(date)) {
                String[] existingTimeParts = existing.getTime().split("-");
                int existingStartTime = convertTimeToMinutes(existingTimeParts[0]);
                int existingEndTime = convertTimeToMinutes(existingTimeParts[1]);

                if (newStartTime < existingEndTime && existingStartTime < newEndTime) {
                    return true;
                }
            }
        }
        return false;
    }

    private int convertTimeToMinutes(String time) {
        String[] parts = time.trim().split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public void removeAppointment(patient p) {
        String patientKey = p.get_pat_name() + "_" + p.get_pat_id();
        if (patientAppointments.containsKey(patientKey)) {
            List<Appointment> appointments = patientAppointments.get(patientKey);
            if (!appointments.isEmpty()) {
                System.out.println("\n📋 Your Appointments:");
                for (int i = 0; i < appointments.size(); i++) {
                    Appointment apt = appointments.get(i);
                    System.out.println((i + 1) + ". 📅 Date: " + apt.getDate() + " | ⏰ Time: " + apt.getTime() +
                            " | 👨‍⚕️ Doctor: Dr. " + apt.getDoctor() +
                            " | 🔖 Booking ID: " + apt.getBuid());
                }

                Scanner scanner = new Scanner(System.in);
                System.out.print("\nEnter the number of the appointment to remove: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= appointments.size()) {
                    Appointment toRemove = appointments.get(choice - 1);

                    String appointmentKey = toRemove.getDoctor() + "_" + toRemove.getDate() + "_" + toRemove.getTime();
                    bookedAppointments.remove(appointmentKey);

                    appointments.remove(choice - 1);

                    System.out.println("✅ Appointment successfully removed!");
                } else {
                    System.out.println("❌ Invalid choice.");
                }
            } else {
                System.out.println("❌ No appointments found for this patient.");
            }
        } else {
            System.out.println("❌ No appointments found for this patient.");
        }
    }

    public boolean viewBookings(String patientName) {
        boolean found = false;
        for (Map.Entry<String, List<Appointment>> entry : patientAppointments.entrySet()) {
            if (entry.getKey().startsWith(patientName + "_")) {
                List<Appointment> appointments = entry.getValue();
                if (!appointments.isEmpty()) {
                    System.out.println("\n📋 Appointments for " + patientName + ":");
                    for (Appointment apt : appointments) {
                        System.out.println("\n✅ APPOINTMENT STATUS SUCCESSFULLY SET TO "+apt.getStatus()+"!");
                        System.out.println("\n****************************************");
                        System.out.println("\t\tCHANGE STATUS RECEIPT");
                        System.out.println("****************************************");
                        System.out.println("👨🏻‍⚕️ Doctor Name:     Dr. " + apt.getDoctor());
                        System.out.println("🆔 UID:             " + apt.getpid());
                        System.out.println("🆔 Booking ID:      " + apt.getBuid());
                        System.out.println("🩺 TREATMENT NAME:  " + apt.getTreatmentname());
                        System.out.println("🩺Specialization:   " + apt.getSpecialization());
                        System.out.println("📅Appointment Date: " + apt.getDate());
                        System.out.println("⏲️Appointment Time: " + apt.getTime());
                        System.out.println("❓Appointment Status: " + (apt.getStatus() != null ? apt.getStatus() : "BOOKED"));
                        System.out.println("----------------------------------------\n\n");
                    }
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("❌ No appointments found for " + patientName);
        }
        return found;
    }

    public boolean viewBooking(String bookingId) {
        for (Map.Entry<String, List<Appointment>> entry : patientAppointments.entrySet()) {
            for (Appointment appointment : entry.getValue()) {
                if (appointment.getBuid() != null && appointment.getBuid().equals(bookingId)) {
                    System.out.println("\n✅ APPOINTMENT STATUS SUCCESSFULLY SET TO "+appointment.getStatus()+"!");
                    System.out.println("\n****************************************");
                    System.out.println("\t\tCHANGE STATUS RECEIPT");
                    System.out.println("****************************************");
                    System.out.println("👨🏻‍⚕️ Doctor Name:     Dr. " + appointment.getDoctor());
                    System.out.println("🆔 UID:             " + appointment.getpid());
                    System.out.println("🆔 Booking ID:      " + appointment.getBuid());
                    System.out.println("🩺 TREATMENT NAME:  " + appointment.getTreatmentname());
                    System.out.println("🩺Specialization:   " + appointment.getSpecialization());
                    System.out.println("📅Appointment Date: " + appointment.getDate());
                    System.out.println("⏲️Appointment Time: " + appointment.getTime());
                    System.out.println("❓Appointment Status: " + (appointment.getStatus() != null ? appointment.getStatus() : "BOOKED"));
                    System.out.println("----------------------------------------\n\n");
                    return true;
                }
            }
        }
        System.out.println("❌ No appointment found with Booking ID: " + bookingId);
        return false;
    }

    public void changeAppointmentStatus(String bookingId, String newStatus) {
        for (Map.Entry<String, List<Appointment>> entry : patientAppointments.entrySet()) {
            for (Appointment appointment : entry.getValue()) {
                if (appointment.getBuid().equalsIgnoreCase(bookingId)) {
                    switch (appointment.getStatus().toUpperCase()) {
                        case "BOOKED":
                            appointment.setStatus(newStatus);
                            appointment.changed(0);
                            System.out.println("✅ Appointment status updated successfully.");
                            return;

                        case "CANCELLED":
                            System.out.println("❌ Your appointment has been cancelled, and you cannot change it.");
                            appointment.changed(1);
                            return;

                        case "ATTENDED":
                            System.out.println("❌ Your appointment has already been marked as attended, and you cannot change it.");
                            appointment.changed(1);
                            return;

                        default:
                            System.out.println("❌ Unknown appointment status.");
                            return;
                    }
                }
            }
        }
        System.out.println("❌ No appointment found with Booking ID: " + bookingId);
    }

    public patient searchBypatname(String bookingId) {
        for (Map.Entry<String, List<Appointment>> entry : patientAppointments.entrySet()) {
            String[] keyParts = entry.getKey().split("_");
            String patientName = keyParts[0];
            int patientId = Integer.parseInt(keyParts[1]);

            for (Appointment apt : entry.getValue()) {
                if (apt.getBuid() != null && apt.getBuid().equals(bookingId)) {
                    return new patient(patientName, "", "", patientId, bookingId);
                }
            }
        }
        return null;
    }

    public String bookingchecking() {
        if (patientAppointments.isEmpty()) {
            return "empty";
        }
        return "notempty";
    }

    public boolean isAppointmentBooked(String appointmentKey) {
        return bookedAppointments.containsKey(appointmentKey);
    }

    public boolean isPatientRegistered(String patientName) {
        for (patient p : registeredPatients) {
            if (p.get_pat_name().equalsIgnoreCase(patientName)) {
                return true;
            }
        }
        return false;
    }

    public patient findPatientByName(String patientName) {
        for (patient p : registeredPatients) {
            if (p.get_pat_name().equalsIgnoreCase(patientName)) {
                return p;
            }
        }
        return null;
    }

    public void generateClinicReport() {
        if (bookedAppointments.isEmpty()) {
            System.out.println("\n❌ No appointments available for the report.");
            return;
        }

        Map<String, List<Appointment>> doctorAppointments = new HashMap<>();

        for (Map.Entry<String, Appointment> entry : bookedAppointments.entrySet()) {
            Appointment appointment = entry.getValue();
            String doctorName = appointment.getDoctor();

            if (!doctorAppointments.containsKey(doctorName)) {
                doctorAppointments.put(doctorName, new ArrayList<>());
            }
            doctorAppointments.get(doctorName).add(appointment);
        }

        System.out.println("\n============================================================================================================");
        System.out.println("                               🏥 CLINIC REPORT - APPOINTMENTS LIST");
        System.out.println("============================================================================================================");

        System.out.printf("| %-20s | %-15s | %-25s | %-9s|\n", "Doctor", "Patient", "Date & Time", "Status");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (Map.Entry<String, List<Appointment>> entry : doctorAppointments.entrySet()) {
            String doctorName = entry.getKey();
            for (Appointment appointment : entry.getValue()) {

                String patientName = "Unknown";
                for (Map.Entry<String, List<Appointment>> patEntry : patientAppointments.entrySet()) {
                    if (patEntry.getValue().contains(appointment)) {
                        String[] keyParts = patEntry.getKey().split("_");
                        patientName = keyParts[0];
                        break;
                    }
                }

                System.out.printf("| %-20s | %-15s | %-25s | %-9s|\n",
                        "Dr. " + doctorName,
                        patientName,
                        appointment.getDate() + " at " + appointment.getTime(),
                        appointment.getStatus());
            }
        }

        List<Map.Entry<String, List<Appointment>>> sortedDoctors = new ArrayList<>(doctorAppointments.entrySet());
        sortedDoctors.sort((d1, d2) -> Long.compare(
                d2.getValue().stream().filter(a -> a.getStatus().equalsIgnoreCase("ATTENDED")).count(),
                d1.getValue().stream().filter(a -> a.getStatus().equalsIgnoreCase("ATTENDED")).count()
        ));

        System.out.println("\n============================================================================================================");
        System.out.println("                                      🏆 PHYSIOTHERAPISTS BY ATTENDED APPOINTMENTS");
        System.out.println("============================================================================================================");
        System.out.printf("| %-20s | %-12s |\n", "Doctor", "Attended Count");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (Map.Entry<String, List<Appointment>> entry : sortedDoctors) {
            long attendedCount = entry.getValue().stream()
                    .filter(a -> a.getStatus().equalsIgnoreCase("ATTENDED"))
                    .count();
            System.out.printf("| %-20s | %-8d |\n", "Dr. " + entry.getKey(), attendedCount);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }

    private Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                return doctor;
            }
        }
        return null;
    }
}