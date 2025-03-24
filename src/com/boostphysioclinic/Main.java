package com.boostphysioclinic;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends treatment {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);
        String answer;

        List<Doctor> doctorList = new ArrayList<>();
        booking bookings = new booking(doctorList);
        doctorList.add(new Doctor("Punnoose", "Physiotherapist", "72 Aviation Avenue", "90744662220", Arrays.asList(
                new Appointment("Week 1: Monday 5th May 2025", "09:30-12:30", "Dr. Punnoose", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 2: Tuesday 13th May 2025", "10:00-13:00", "Dr. Punnoose", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 3: Wednesday 21st May 2025", "11:00-14:00", "Dr. Punnoose", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 4: Thursday 29th May 2025", "12:00-15:00", "Dr. Punnoose", "Physiotherapist", 0,"","","",0)
        ),"Mobilisation"));

        doctorList.add(new Doctor("Smith", "Physiotherapist", "34 Medway Street", "90123456789", Arrays.asList(
                new Appointment("Week 1: Tuesday 6th May 2025", "08:30-11:30", "Dr. Smith", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 2: Wednesday 14th May 2025", "09:00-12:00", "Dr. Smith", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 3: Thursday 22nd May 2025", "10:30-13:30", "Dr. Smith", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 4: Friday 30th May 2025", "11:00-14:00", "Dr. Smith", "Physiotherapist", 0,"","","",0)
        ),"Acupuncture"));

        doctorList.add(new Doctor("Mathew", "Physiotherapist", "56 Oakwood Lane", "91234567890", Arrays.asList(
                new Appointment("Week 1: Wednesday 7th May 2025", "09:00-12:00", "Dr. Mathew", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 2: Thursday 15th May 2025", "10:30-13:30", "Dr. Mathew", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 3: Friday 23rd May 2025", "11:00-14:00", "Dr. Mathew", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 4: Monday 2nd June 2025", "12:00-15:00", "Dr. Mathew", "Physiotherapist", 0,"","","",0)
        ),"Pool Rehabilitation"));

        doctorList.add(new Doctor("John", "Physiotherapist", "78 Willow Street", "92345678901", Arrays.asList(
                new Appointment("Week 1: Thursday 8th May 2025", "08:30-11:30", "Dr. John", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 2: Friday 16th May 2025", "09:00-12:00", "Dr. John", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 3: Monday 26th May 2025", "10:30-13:30", "Dr. John", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 4: Tuesday 3rd June 2025", "11:00-14:00", "Dr. John", "Physiotherapist", 0,"","","",0)
        ),"Massage"));

        doctorList.add(new Doctor("Paul", "Physiotherapist", "90 Birchwood Road", "93456789012", Arrays.asList(
                new Appointment("Week 1: Friday 9th May 2025", "09:30-12:30", "Dr. Paul", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 2: Monday 19th May 2025", "10:00-13:00", "Dr. Paul", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 3: Tuesday 27th May 2025", "11:00-14:00", "Dr. Paul", "Physiotherapist", 0,"","","",0),
                new Appointment("Week 4: Wednesday 4th June 2025", "12:00-15:00", "Dr. Paul", "Physiotherapist", 0,"","","",0)
        ),"Neural Mobilisation"));

        // Patients
        patient patient1 = new patient("Alice Johnson", "10 Green St, London", "9123456789", 168, "20250305123001");
        patient patient2 = new patient("Bob Williams", "20 Red St, Manchester", "9234567890", 890, "20250306104502");
        patient patient3 = new patient("Charlie Brown", "30 Blue St, Birmingham", "9345678901", 901, "20250307153003");
        patient patient4 = new patient("Henry Smith", "33 Blue St, Birmingham", "9456789012", 567, "202503071563004");

        patient patient5 = new patient("David Wilson", "40 Green Ave, Liverpool", "9567890123", 234, "20250308121505");
        patient patient6 = new patient("Emma Davis", "50 Red Road, Leeds", "9678901234", 456, "20250309104506");
        patient patient7 = new patient("Franklin White", "60 Blue Lane, Bristol", "9789012345", 789, "20250310153007");
        patient patient8 = new patient("Grace Hall", "70 Yellow St, Sheffield", "9890123456", 123, "20250311123008");
        patient patient9 = new patient("Harry Green", "80 Purple Ave, Edinburgh", "9901234567", 456, "20250312104509");
        patient patient10 = new patient("Isabella Moore", "90 Orange Rd, Glasgow", "9012345678", 678, "20250313153010");
        patient patient11 = new patient("Jack Turner", "100 White Blvd, Cardiff", "9123456789", 890, "20250314123011");
        patient patient12 = new patient("Katie Adams", "110 Black St, Belfast", "9234567890", 234, "20250315104512");
        patient patient13 = new patient("Liam Scott", "120 Grey Lane, York", "9345678901", 345, "20250316153013");
        patient patient14 = new patient("Mia Carter", "130 Brown Ave, Newcastle", "9456789012", 567, "20250317123014");

// Hardcoded Appointments for Patients with Correct Doctor Names
        bookings.bookAppointmenthardcode(patient1, doctorList.get(0), "Week 1: Monday 5th May 2025", "09:30-12:30", 678, "Punnoose", "20250305123001");
        bookings.bookAppointmenthardcode(patient2, doctorList.get(1), "Week 1: Tuesday 6th May 2025", "08:30-11:30", 890, "Smith", "20250306104502");
        bookings.bookAppointmenthardcode(patient3, doctorList.get(2), "Week 1: Wednesday 7th May 2025", "10:00-12:00", 901, "Mathew", "20250307153003");
        bookings.bookAppointmenthardcode(patient4, doctorList.get(3), "Week 1: Thursday 8th May 2025", "14:00-16:30", 567, "Paul", "202503071563004");

        bookings.bookAppointmenthardcode(patient5, doctorList.get(4), "Week 2: Friday 9th May 2025", "09:00-11:30", 234, "John", "20250308121505");
        bookings.bookAppointmenthardcode(patient6, doctorList.get(0), "Week 2: Monday 12th May 2025", "09:30-12:30", 456, "Punnoose", "20250309104506");
        bookings.bookAppointmenthardcode(patient7, doctorList.get(1), "Week 2: Tuesday 13th May 2025", "10:00-12:00", 789, "Smith", "20250310153007");
        bookings.bookAppointmenthardcode(patient8, doctorList.get(2), "Week 2: Wednesday 14th May 2025", "11:30-14:00", 123, "Mathew", "20250311123008");
        bookings.bookAppointmenthardcode(patient9, doctorList.get(3), "Week 3: Thursday 15th May 2025", "12:00-14:00", 456, "Paul", "20250312104509");
        bookings.bookAppointmenthardcode(patient10, doctorList.get(4), "Week 3: Friday 16th May 2025", "08:30-10:30", 678, "John", "20250313153010");

        bookings.bookAppointmenthardcode(patient11, doctorList.get(0), "Week 4: Monday 19th May 2025", "09:00-11:00", 890, "Punnoose", "20250314123011");
        bookings.bookAppointmenthardcode(patient12, doctorList.get(1), "Week 4: Tuesday 20th May 2025", "10:30-12:30", 234, "Smith", "20250315104512");
        bookings.bookAppointmenthardcode(patient13, doctorList.get(2), "Week 5: Wednesday 21st May 2025", "12:00-14:30", 345, "Mathew", "20250316153013");
        bookings.bookAppointmenthardcode(patient14, doctorList.get(3), "Week 5: Thursday 22nd May 2025", "14:30-16:30", 567, "Paul", "20250317123014");

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
                case 4: bookings.generateClinicReport();
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
        patient patient = new patient(user_name,user_address,telephone,0,"");
        //String uid = (user_name.length() > 3 ? user_name.substring(0, 3) : user_name) + telephone.substring(telephone.length() - 3);
        System.out.println("\n🔹 UNIQUE ID GENERATED: " + patient.hashCode());
        int pid = patient.hashCode();
        System.out.println("⚠️ Please save or remember this ID for future use.");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String buid = sdf.format(new Date());
        System.out.println("\nHow would you like to book an appointment?");
        System.out.println("1️⃣  Search by area of expertise");
        System.out.println("2️⃣  Search by doctor's name\n");

        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            if (user_input.hasNextInt()) {

                choice = user_input.nextInt();

                if (choice == 1 || choice == 2) {
                    user_input.nextLine();
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                user_input.nextLine();
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
                           found3= bookSelectedAppointment(user_input, bookings, doctorList, area_of_expertise, patient, pid, doctor_namee, buid);
                        }while (!found3);
                    }
                }while (!found);
                break;
            case 2:
                boolean found2;
                do{
                    System.out.print("\nEnter doctor's full name: ");
                    doctor_namee = user_input.nextLine();
                    found2=bookings.searchBydocname(doctor_namee);
                    bookSelectedAppointment(user_input, bookings, doctorList, null, patient,pid,doctor_namee,buid);
                }while (!found2);
                break;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }

    private static boolean bookSelectedAppointment(Scanner user_input, booking bookings, List<Doctor> doctorList, String specialization, patient patient, int pid, String doctor_namee, String buid) {
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
        int choice1;
        do {
            System.out.print("\nEnter the week of appointment for Dr." + doctorname + " : ");
            choice1 = user_input.nextInt();
            user_input.nextLine();  // Consume newline

            if (choice1 < 1 || choice1 > selectedDoctor.getAvailableAppointments().size()) {
                System.out.println("❌ Invalid selection. Please enter a valid week.");
            }
        } while (choice1 < 1 || choice1 > selectedDoctor.getAvailableAppointments().size());



        System.out.println("\n");
        Appointment chosenAppointment = selectedDoctor.getAvailableAppointments().get(choice1 - 1);
        bookings.bookAppointment(patient, selectedDoctor, chosenAppointment.getDate(), chosenAppointment.getTime(),pid,doctor_namee,buid);
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
        String patname;

        if (!bookings.bookingchecking().equals("empty")) {
            System.out.println("Enter Patient Name: ");
            patname = user_input.nextLine();
            System.out.println("\n");
            boolean found = bookings.viewBookings(patname); // Call it once
            if (found) {  // Only ask for status update if an appointment is found
                System.out.println("Do you want to update the status of an appointment?");
                System.out.println("Enter your choice:");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Your choice:");
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
        System.out.println("\nEnter From The Option Below ");
        System.out.println("1.cancel the appointment");
        System.out.println("2.make the appointment as attended");
        System.out.println("\nYour Choice :");
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

        System.out.println("\nEnter Booking ID: \n");
        String BookingId = user_input.nextLine();
        changestatus(user_input,bookings ,BookingId);

    }

}