import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);
        String answer;

        List<Doctor> doctorList = new ArrayList<>();
        booking bookings = new booking(doctorList);
        doctorList.add(new Doctor("Punnoose", "Physiotherapist", "72 Aviation Avenue", "90744662220", Arrays.asList(
                new Appointment("Week 1: Monday 5th May 2025", "09:30-12:30", "Dr. Punnoose", "Physiotherapist", ""),
                new Appointment("Week 2: Tuesday 13th May 2025", "10:00-13:00", "Dr. Punnoose", "Physiotherapist", ""),
                new Appointment("Week 3: Wednesday 21st May 2025", "11:00-14:00", "Dr. Punnoose", "Physiotherapist", ""),
                new Appointment("Week 4: Thursday 29th May 2025", "12:00-15:00", "Dr. Punnoose", "Physiotherapist", "")
        )));

        doctorList.add(new Doctor("Smith", "Physiotherapist", "34 Medway Street", "90123456789", Arrays.asList(
                new Appointment("Week 1: Tuesday 6th May 2025", "08:30-11:30", "Dr. Smith", "Physiotherapist", ""),
                new Appointment("Week 2: Wednesday 14th May 2025", "09:00-12:00", "Dr. Smith", "Physiotherapist", ""),
                new Appointment("Week 3: Thursday 22nd May 2025", "10:30-13:30", "Dr. Smith", "Physiotherapist", ""),
                new Appointment("Week 4: Friday 30th May 2025", "11:00-14:00", "Dr. Smith", "Physiotherapist", "")
        )));

        doctorList.add(new Doctor("Mathew", "Physiotherapist", "56 Oakwood Lane", "91234567890", Arrays.asList(
                new Appointment("Week 1: Wednesday 7th May 2025", "09:00-12:00", "Dr. Mathew", "Physiotherapist", ""),
                new Appointment("Week 2: Thursday 15th May 2025", "10:30-13:30", "Dr. Mathew", "Physiotherapist", ""),
                new Appointment("Week 3: Friday 23rd May 2025", "11:00-14:00", "Dr. Mathew", "Physiotherapist", ""),
                new Appointment("Week 4: Monday 2nd June 2025", "12:00-15:00", "Dr. Mathew", "Physiotherapist", "")
        )));

        doctorList.add(new Doctor("John", "Physiotherapist", "78 Willow Street", "92345678901", Arrays.asList(
                new Appointment("Week 1: Thursday 8th May 2025", "08:30-11:30", "Dr. John", "Physiotherapist", ""),
                new Appointment("Week 2: Friday 16th May 2025", "09:00-12:00", "Dr. John", "Physiotherapist", ""),
                new Appointment("Week 3: Monday 26th May 2025", "10:30-13:30", "Dr. John", "Physiotherapist", ""),
                new Appointment("Week 4: Tuesday 3rd June 2025", "11:00-14:00", "Dr. John", "Physiotherapist", "")
        )));

        doctorList.add(new Doctor("Paul", "Physiotherapist", "90 Birchwood Road", "93456789012", Arrays.asList(
                new Appointment("Week 1: Friday 9th May 2025", "09:30-12:30", "Dr. Paul", "Physiotherapist", ""),
                new Appointment("Week 2: Monday 19th May 2025", "10:00-13:00", "Dr. Paul", "Physiotherapist", ""),
                new Appointment("Week 3: Tuesday 27th May 2025", "11:00-14:00", "Dr. Paul", "Physiotherapist", ""),
                new Appointment("Week 4: Wednesday 4th June 2025", "12:00-15:00", "Dr. Paul", "Physiotherapist", "")
        )));



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
        String uid = (user_name.length() > 3 ? user_name.substring(0, 3) : user_name) + telephone.substring(telephone.length() - 3);
        System.out.println("\n🔹 UNIQUE ID GENERATED: " + uid);
        System.out.println("⚠️ Please save or remember this ID for future use.");
        patient patient = new patient(user_name, user_address, telephone,uid);
        System.out.println("\nHow would you like to book an appointment?");
        System.out.println("1️⃣  Search by area of expertise");
        System.out.println("2️⃣  Search by doctor's name");
        System.out.print("Enter your choice: ");
        int choice = user_input.nextInt();
        user_input.nextLine();
        String doctor_namee;
        switch (choice) {
            case 1:
                System.out.print("Enter specialization: ");
                String area_of_expertise = user_input.nextLine();
                bookings.searchByspecialization(area_of_expertise);
                System.out.println("Enter the doctor's full name: ");
                doctor_namee = user_input.nextLine();
                bookSelectedAppointment(user_input, bookings, doctorList, area_of_expertise, patient,uid,doctor_namee);
                break;
            case 2:
                System.out.print("Enter doctor's full name: ");
                doctor_namee = user_input.nextLine();
                bookings.searchBydocname(doctor_namee);
                bookSelectedAppointment(user_input, bookings, doctorList, null, patient,uid,doctor_namee);
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }

    private static void bookSelectedAppointment(Scanner user_input, booking bookings, List<Doctor> doctorList, String specialization, patient patient,String uid,String doctor_namee) {
        Doctor selectedDoctor = null;

        for (Doctor doctor : doctorList) {

            if (specialization == null || doctor.getSpecialization().equalsIgnoreCase(specialization)) {

                if (doctor.getName().equalsIgnoreCase(doctor_namee)) {
                    selectedDoctor = doctor;
                    break;
                }
            }

        }
        if (selectedDoctor == null) {
            System.out.println("❌ No doctor found.");
            return;
        }
        System.out.print("Enter the week of appointment: ");
        int choice1 = user_input.nextInt();
        user_input.nextLine();

        if (choice1 < 1 || choice1 > selectedDoctor.getAvailableAppointments().size()) {
            System.out.println("❌ Invalid selection.");
            return;
        }

        Appointment chosenAppointment = selectedDoctor.getAvailableAppointments().get(choice1 - 1);
        bookings.bookAppointment(patient, selectedDoctor, chosenAppointment.getDate(), chosenAppointment.getTime(),uid,doctor_namee);
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