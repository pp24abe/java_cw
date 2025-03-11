import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);
        String answer;

        Doctor doctor1 = new Doctor("Punnoose", "Physiotherapist", "72 Aviation Avenue", "90744662220", Arrays.asList(
                new Appointment("1. Thursday 1st May 2025", "09:30-12:30", "Punnoose", "Physiotherapist",""),
                new Appointment("2. Friday 2nd May 2025", "10:00-13:00", "Punnoose", "Physiotherapist","")
        ));

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(doctor1);
        booking bookings = new booking(doctorList);


        do {
            System.out.println("\n---------------------------------------------");
            System.out.println("\t Welcome to the Appointment Booking System");
            System.out.println("---------------------------------------------");
            System.out.println("1. Make appointment");
            System.out.println("2. Remove appointment");
            System.out.println("3. View appointment");
            System.out.println("4. Print report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int option = user_input.nextInt();
            user_input.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    makeAppointment(user_input, bookings, doctorList);
                    break;
                case 2:
                    removeAppointment(user_input, bookings);
                    break;
                case 3:
                    bookings.viewBookings();
                    break;
                case 5:
                    System.out.println("\nThank you for using the Appointment Booking System. Have a great day!");
                    user_input.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
            System.out.print("\nWould you like to perform another action? (Y/N): ");
            answer = user_input.nextLine();
        } while (answer.equalsIgnoreCase("Y"));

        System.out.println("\nThank you for using the Appointment Booking System. Have a great day!");
        user_input.close();
    }

    private static void makeAppointment(Scanner user_input, booking bookings, List<Doctor> doctorList) {
        System.out.print("Please enter the patient's full name: ");
        String user_name = user_input.nextLine();
        System.out.print("Please enter the patient's address: ");
        String user_address = user_input.nextLine();
        System.out.print("Please enter the patient's phone number: ");
        String telephone = user_input.nextLine();
        if (telephone.length() != 10) {
            do {
                System.out.print("Please enter a valid phone number: ");
                telephone = user_input.nextLine();
            }while (telephone.length() != 10);
        }
        String uid = (user_name.length() > 3 ? user_name.substring(0, 3) : user_name) + (telephone.length() >= 3 ? telephone.substring(telephone.length() - 3) : telephone);
        System.out.println("\n🔹 UNIQUE ID GENERATED: " + uid);
        System.out.println("⚠️ Please save or remember this ID for future use.");
        patient patient = new patient(user_name, user_address, telephone,uid);
        System.out.println("\nHow would you like to book an appointment?");
        System.out.println("1️⃣  Search by area of expertise");
        System.out.println("2️⃣  Search by doctor's name");
        System.out.print("Enter your choice: ");
        int choice = user_input.nextInt();
        user_input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter specialization: ");
                String area_of_expertise = user_input.nextLine();
                bookings.searchByspecialization(area_of_expertise);
                bookSelectedAppointment(user_input, bookings, doctorList, area_of_expertise, null, patient,uid);
                break;
            case 2:
                System.out.print("Enter doctor's full name: ");
                String doctor_name = user_input.nextLine();
                bookings.searchBydocname(doctor_name);
                bookSelectedAppointment(user_input, bookings, doctorList, null, doctor_name, patient,uid);
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }

    private static void bookSelectedAppointment(Scanner user_input, booking bookings, List<Doctor> doctorList, String specialization, String doctor_name, patient patient,String uid) {
        Doctor selectedDoctor = null;

        for (Doctor doctor : doctorList) {
            if ((specialization != null && doctor.getSpecialization().equalsIgnoreCase(specialization)) ||
                    (doctor_name != null && doctor.getName().equalsIgnoreCase(doctor_name))) {
                selectedDoctor = doctor;
                break;
            }
        }

        if (selectedDoctor == null) {
            System.out.println("❌ No doctor found.");
            return;
        }

        System.out.print("Enter appointment number: ");
        int choice1 = user_input.nextInt();
        user_input.nextLine();

        if (choice1 < 1 || choice1 > selectedDoctor.getAvailableAppointments().size()) {
            System.out.println("❌ Invalid selection.");
            return;
        }

        Appointment chosenAppointment = selectedDoctor.getAvailableAppointments().get(choice1 - 1);
        bookings.bookAppointment(patient, selectedDoctor, chosenAppointment.getDate(), chosenAppointment.getTime(),uid);
    }

    private static void removeAppointment(Scanner user_input, booking bookings) {
        System.out.print("Enter patient ID: ");
        String patientid = user_input.nextLine();
        patient p = bookings.searchBypatname(patientid);

        if (p == null) {
            System.out.println("❌ No appointment found for the patient with the given ID.");
            return;
        }

        bookings.removeAppointment(p);
    }
}