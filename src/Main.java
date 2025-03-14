import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);
        String answer;

        List<Doctor> doctorList = new ArrayList<>();
        booking bookings = new booking(doctorList);
        treatment treatment = new treatment();
        doctorList.add(new Doctor("Punnoose", "Physiotherapist", "72 Aviation Avenue", "90744662220", Arrays.asList(
                new Appointment("Week 1: Monday 5th May 2025", "09:30-12:30", "Dr. Punnoose", "Physiotherapist", "","","",""),
                new Appointment("Week 2: Tuesday 13th May 2025", "10:00-13:00", "Dr. Punnoose", "Physiotherapist", "","","",""),
                new Appointment("Week 3: Wednesday 21st May 2025", "11:00-14:00", "Dr. Punnoose", "Physiotherapist", "","","",""),
                new Appointment("Week 4: Thursday 29th May 2025", "12:00-15:00", "Dr. Punnoose", "Physiotherapist", "","","","")
        ),"Mobilisation"));

        doctorList.add(new Doctor("Smith", "Physiotherapist", "34 Medway Street", "90123456789", Arrays.asList(
                new Appointment("Week 1: Tuesday 6th May 2025", "08:30-11:30", "Dr. Smith", "Physiotherapist", "","","",""),
                new Appointment("Week 2: Wednesday 14th May 2025", "09:00-12:00", "Dr. Smith", "Physiotherapist", "","","",""),
                new Appointment("Week 3: Thursday 22nd May 2025", "10:30-13:30", "Dr. Smith", "Physiotherapist", "","","",""),
                new Appointment("Week 4: Friday 30th May 2025", "11:00-14:00", "Dr. Smith", "Physiotherapist", "","","","")
        ),"Acupuncture"));

        doctorList.add(new Doctor("Mathew", "Physiotherapist", "56 Oakwood Lane", "91234567890", Arrays.asList(
                new Appointment("Week 1: Wednesday 7th May 2025", "09:00-12:00", "Dr. Mathew", "Physiotherapist", "","","",""),
                new Appointment("Week 2: Thursday 15th May 2025", "10:30-13:30", "Dr. Mathew", "Physiotherapist", "","","",""),
                new Appointment("Week 3: Friday 23rd May 2025", "11:00-14:00", "Dr. Mathew", "Physiotherapist", "","","",""),
                new Appointment("Week 4: Monday 2nd June 2025", "12:00-15:00", "Dr. Mathew", "Physiotherapist", "","","","")
        ),"Pool Rehabilitation"));

        doctorList.add(new Doctor("John", "Physiotherapist", "78 Willow Street", "92345678901", Arrays.asList(
                new Appointment("Week 1: Thursday 8th May 2025", "08:30-11:30", "Dr. John", "Physiotherapist", "","","",""),
                new Appointment("Week 2: Friday 16th May 2025", "09:00-12:00", "Dr. John", "Physiotherapist", "","","",""),
                new Appointment("Week 3: Monday 26th May 2025", "10:30-13:30", "Dr. John", "Physiotherapist", "","","",""),
                new Appointment("Week 4: Tuesday 3rd June 2025", "11:00-14:00", "Dr. John", "Physiotherapist", "","","","")
        ),"Massage"));

        doctorList.add(new Doctor("Paul", "Physiotherapist", "90 Birchwood Road", "93456789012", Arrays.asList(
                new Appointment("Week 1: Friday 9th May 2025", "09:30-12:30", "Dr. Paul", "Physiotherapist", "","","",""),
                new Appointment("Week 2: Monday 19th May 2025", "10:00-13:00", "Dr. Paul", "Physiotherapist", "","","",""),
                new Appointment("Week 3: Tuesday 27th May 2025", "11:00-14:00", "Dr. Paul", "Physiotherapist", "","","",""),
                new Appointment("Week 4: Wednesday 4th June 2025", "12:00-15:00", "Dr. Paul", "Physiotherapist", "","","","")
        ),"Neural Mobilisation"));



        do {
            System.out.println("\n---------------------------------------------");
            System.out.println("\t Welcome to the Appointment Booking System");
            System.out.println("---------------------------------------------");
            System.out.println("1. Make appointment");
            System.out.println("2. Remove appointment");
            System.out.println("3. View appointment");
            System.out.println("4. Print report");
            System.out.println("5. Exit\n");

            int option;
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    option = user_input.nextInt();
                    user_input.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input! Please enter a number.\n");
                    user_input.nextLine();
                }
            }


            switch (option) {
                case 1:
                    makeAppointment(user_input, bookings, doctorList);
                    break;
                case 2:
                    removeAppointment(user_input, bookings);
                    break;
                case 3:
                     viewbooking(user_input,bookings);
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
        System.out.print("\nPlease enter the patient's full name: ");
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String buid = sdf.format(new Date());
        patient patient = new patient(user_name, user_address, telephone,uid,buid);
        System.out.println("\nHow would you like to book an appointment?");
        System.out.println("1️⃣  Search by area of expertise");
        System.out.println("2️⃣  Search by doctor's name\n");

        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            if (user_input.hasNextInt()) {

                choice = user_input.nextInt();

                if (choice == 1 || choice == 2) { // Correct condition
                    user_input.nextLine(); // Consume the newline
                    break; // Exit the loop
                } else {
                    System.out.println("Invalid choice! Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                user_input.nextLine(); // Clear invalid input
            }
        }
        String doctor_namee;

        switch (choice) {
            case 1:
                boolean found ,found3;
                do{
                    System.out.print("\nEnter specialization: ");
                    String area_of_expertise = user_input.nextLine();
                    found= bookings.searchByspecialization(area_of_expertise);
                    if(found){
                        do {
                            System.out.println("\nEnter the doctor's full name: ");
                            doctor_namee = user_input.nextLine();
                            bookings.showtimetable(doctor_namee);
                           found3= bookSelectedAppointment(user_input, bookings, doctorList, area_of_expertise, patient, uid, doctor_namee, buid);
                        }while (found3 == false);
                    }
                }while (found == false);
                break;
            case 2:
                boolean found2;
                do{
                    System.out.print("\nEnter doctor's full name: ");
                    doctor_namee = user_input.nextLine();
                    found2=bookings.searchBydocname(doctor_namee);
                    bookSelectedAppointment(user_input, bookings, doctorList, null, patient,uid,doctor_namee,buid);
                }while (found2 == false);
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }

    private static boolean bookSelectedAppointment(Scanner user_input, booking bookings, List<Doctor> doctorList, String specialization, patient patient, String uid, String doctor_namee, String buid) {
        Doctor selectedDoctor = null;
        String doctorname="";
        for (Doctor doctor : doctorList) {

            if (specialization == null || doctor.getSpecialization().equalsIgnoreCase(specialization)) {

                if (doctor.getName().equalsIgnoreCase(doctor_namee)) {
                    selectedDoctor = doctor;
                    doctorname=doctor.getName();
                    break;
                }
            }

        }
        if (selectedDoctor == null) {
            System.out.println("❌ No doctor found.");
            return false;
        }

        System.out.print("\nEnter the week of appointment for Dr."+doctorname+" : ");
        int choice1 = user_input.nextInt();
        user_input.nextLine();

        if (choice1 < 1 || choice1 > selectedDoctor.getAvailableAppointments().size()) {
            System.out.println("❌ Invalid selection.");
            return false;
        }
        System.out.println("\n");
        Appointment chosenAppointment = selectedDoctor.getAvailableAppointments().get(choice1 - 1);
        bookings.bookAppointment(patient, selectedDoctor, chosenAppointment.getDate(), chosenAppointment.getTime(),uid,doctor_namee,buid);
        return true;
    }


    private static void removeAppointment(Scanner user_input, booking bookings) {
        System.out.print("Enter Booking ID: ");
        String BookingId = user_input.nextLine();
        patient p = bookings.searchBypatname(BookingId);

        if (p == null) {
            System.out.println("❌ No appointment found for the patient with the given ID.");
            return;
        }

        bookings.removeAppointment(p);
    }

    private static void viewbooking(Scanner user_input, booking bookings) {
        String patname = "";

        if (!bookings.bookingchecking().equals("empty")) {
            System.out.println("Enter Patient Name: ");
            patname = user_input.nextLine();

            boolean found = bookings.viewBookings(patname); // Call it once

            if (found) {  // Only ask for status update if an appointment is found
                System.out.println("Do you want to update the status of an appointment?");
                System.out.println("Enter your choice:");
                System.out.println("1. Yes");
                System.out.println("2. No");

                int choice = user_input.nextInt();
                user_input.nextLine();  // Consume newline

                if (choice == 1) {
                    search(user_input, bookings);
                }
            }
        } else {
            System.out.println("No booking found.");
        }
    }
    private static void changestatus(Scanner user_input, booking bookings,String bookingId) {
        System.out.println("Enter From The Option Below ");
        System.out.println("1.cancel the appointment");
        System.out.println("2.make the appointment as attended");
        int choice = user_input.nextInt();
        switch (choice) {
            case 1:
                bookings.changeAppointmentStatus(bookingId, "CANCELLED");
                break;
            case 2:
                bookings.changeAppointmentStatus(bookingId, "ATTENDED");
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
        bookings.viewBooking(bookingId);
        user_input.nextLine();
    }
    private static void search(Scanner user_input, booking bookings) {
        System.out.println("Enter Booking ID: ");
        String BookingId = user_input.nextLine();
        changestatus(user_input,bookings ,BookingId);

    }
    private static void printreport(Scanner user_input, booking bookings) {




    }
}