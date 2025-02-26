import java.util.*;

public class Main {
    public static void main(String[] args) {
        String doctor_name = "";
        String area_of_expertise = "";
        Scanner user_input = new Scanner(System.in);
        String answer="";

        do {
            System.out.println("\n---------------------------------------------");
            System.out.println("\t Welcome to the Appointment Booking System");
            System.out.println("---------------------------------------------");

            System.out.print("Please enter the patient's full name: ");
            var user_name = user_input.nextLine();

            System.out.print("Please enter the patient's address: ");
            var user_address = user_input.nextLine();

            System.out.print("Please enter the patient's phone number: ");
            String telephone = user_input.nextLine();

            patient patient = new patient();
            patient.set_pat_name(user_name);
            patient.set_pat_address(user_address);
            patient.set_pat_phno(telephone);

            Doctor doctor1 = new Doctor("Punnoose", "Physiotherapist", "72 Aviation Avenue", "90744662220", Arrays.asList(
                    new Appointment("1. Thursday 1st May 2025", "09:30-12:30"),
                    new Appointment("2. Friday 2nd May 2025", "10:00-13:00")
            ));

            List<Doctor> doctorList = new ArrayList<>();
            doctorList.add(doctor1);
            booking booking = new booking(doctorList);

            System.out.println("\nHow would you like to book an appointment?");
            System.out.println("1️⃣  Search by area of expertise to view available physiotherapists and their treatments.");
            System.out.println("2️⃣  Search for a specific physiotherapist by name.");
            System.out.print("Please enter your choice (1 or 2): ");

            int choice = user_input.nextInt();
            user_input.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("\nPlease enter the specialization you are looking for (e.g., Physiotherapist): ");
                area_of_expertise = user_input.nextLine();

                booking.searchByspecialization(area_of_expertise);
                Doctor selectedDoctor = null;

                for (Doctor doctor : doctorList) {
                    if (doctor.getSpecialization().equalsIgnoreCase(area_of_expertise)) {
                        selectedDoctor = doctor;
                        break;
                    }
                }

                if (selectedDoctor == null) {
                    System.out.println("❌ Sorry, no doctor with that specialization was found.");
                    continue;
                }

                System.out.print("\nPlease enter the number corresponding to the appointment you wish to book: ");
                int choice1 = user_input.nextInt();
                user_input.nextLine(); // Consume newline

                if (choice1 < 1 || choice1 > selectedDoctor.getAvailableAppointments().size()) {
                    System.out.println("❌ Invalid selection. Please try again.");
                    continue;
                }

                Appointment chosenAppointment = selectedDoctor.getAvailableAppointments().get(choice1 - 1);
                booking.bookAppointment(patient, selectedDoctor, chosenAppointment.getDate(), chosenAppointment.getTime());

            } else if (choice == 2) {
                System.out.print("\nPlease enter the full name of the doctor: ");
                doctor_name = user_input.nextLine();

                booking.searchBydocname(doctor_name);
                Doctor selectedDoctor = null;

                for (Doctor doctor : doctorList) {
                    if (doctor.getName().equalsIgnoreCase(doctor_name)) {
                        selectedDoctor = doctor;
                        break;
                    }
                }

                if (selectedDoctor == null) {
                    System.out.println("❌ Sorry, no doctor with that name was found.");
                    continue;
                }

                System.out.print("\nPlease enter the number corresponding to the appointment you wish to book: ");
                int choice1 = user_input.nextInt();
                user_input.nextLine(); // Consume newline

                if (choice1 < 1 || choice1 > selectedDoctor.getAvailableAppointments().size()) {
                    System.out.println("❌ Invalid selection. Please try again.");
                    continue;
                }

                Appointment chosenAppointment = selectedDoctor.getAvailableAppointments().get(choice1 - 1);
                booking.bookAppointment(patient, selectedDoctor, chosenAppointment.getDate(), chosenAppointment.getTime());
            }

            System.out.print("\nWould you like to book another appointment? (Y/N): ");
            answer = user_input.nextLine();

        } while (answer.equalsIgnoreCase("Y"));

        System.out.println("\nThank you for using the Appointment Booking System. Have a great day!");
        user_input.close();
    }
}
