import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bookingTest {

    private booking bookingSystem;
    private Doctor doctor1;
    private Doctor doctor2;
    private patient patient1;
    private patient patient2;
    private List<Appointment> doctor1Appointments;
    private List<Appointment> doctor2Appointments;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   // private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        doctor1Appointments = new ArrayList<>();
        doctor1Appointments.add(new Appointment("Week 1: Monday", "10:00-11:00", null, null, 0, null, null, null, 0));
        doctor1Appointments.add(new Appointment("Week 1: Tuesday", "14:00-15:00", null, null, 0, null, null, null, 0));

        doctor2Appointments = new ArrayList<>();
        doctor2Appointments.add(new Appointment("Week 1: Wednesday", "09:00-10:00", null, null, 0, null, null, null, 0));
        doctor2Appointments.add(new Appointment("Week 1: Thursday", "13:00-14:00", null, null, 0, null, null, null, 0));

        doctor1 = new Doctor("Smith", "Cardiology", "Clinic A", "1234567890", doctor1Appointments, "Heart Treatment");
        doctor2 = new Doctor("Brown", "Neurology", "Clinic B", "0987654321", doctor2Appointments, "Brain Treatment");

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        bookingSystem = new booking(doctors);
        patient1 = new patient("John Doe", "123 Main St", "9876543210", 101, "BID101");
        patient2 = new patient("Jane Smith", "456 Oak Ave", "5551234567", 102, "BID102");

        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Test searching for doctors by specialization")
    void testSearchBySpecialization() {
        outContent.reset();
        assertTrue(bookingSystem.searchByspecialization("Cardiology"));
        assertTrue(outContent.toString().contains("Smith"));
        outContent.reset();
        assertFalse(bookingSystem.searchByspecialization("Dermatology"));
        assertTrue(outContent.toString().contains("No doctors found with specialization: Dermatology"));
    }

    @Test
    @DisplayName("Test searching for doctors by name")
    void testSearchByDoctorName() {
        assertTrue(bookingSystem.searchBydocname("Smith"));
        assertFalse(bookingSystem.searchBydocname("Nonexistent"));
    }

    @Test
    @DisplayName("Test booking an appointment")
    void testBookAppointment() {
        outContent.reset();
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        assertTrue(outContent.toString().contains("APPOINTMENT SUCCESSFULLY BOOKED"));
        outContent.reset();
        assertTrue(bookingSystem.viewBookings("John Doe",101));
        assertTrue(outContent.toString().contains("Dr. Smith"));
    }

    @Test
    @DisplayName("Test booking an already booked appointment")
    void testBookAlreadyBookedAppointment() {
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        outContent.reset();
        bookingSystem.bookAppointment(patient2, doctor1, "Week 1: Monday", "10:00-11:00", 102, "Smith", "BID102");
        assertTrue(outContent.toString().contains("This appointment is already booked"));
    }

    @Test
    @DisplayName("Test patient with overlapping appointments")
    void testOverlappingAppointments() {
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        outContent.reset();
        bookingSystem.bookAppointment(patient1, doctor2, "Week 1: Monday", "10:00-11:00", 101, "Brown", "BID103");
        assertTrue(outContent.toString().contains("already have an appointment scheduled during this time"));
    }

    @Test
    @DisplayName("Test viewing bookings by patient name")
    void testViewBookingsByPatientName() {
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        outContent.reset();
        assertTrue(bookingSystem.viewBookings("John Doe",101));
        assertTrue(outContent.toString().contains("Appointments for John Doe"));
        outContent.reset();
        assertFalse(bookingSystem.viewBookings("Nonexistent Patient",101));
        assertTrue(outContent.toString().contains("No appointments found for Nonexistent Patient"));
    }

    @Test
    @DisplayName("Test viewing booking by booking ID")
    void testViewBookingByID() {
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        outContent.reset();
        assertTrue(bookingSystem.viewBooking("BID101"));
        assertTrue(outContent.toString().contains("Booking ID:      BID101"));
        outContent.reset();
        assertFalse(bookingSystem.viewBooking("INVALID_ID"));
        assertTrue(outContent.toString().contains("No appointment found with Booking ID: INVALID_ID"));
    }

    @Test
    @DisplayName("Test changing appointment status")
    void testChangeAppointmentStatus() {
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        outContent.reset();
        bookingSystem.changeAppointmentStatus("BID101", "ATTENDED");
        assertTrue(outContent.toString().contains("Appointment status updated successfully"));
        outContent.reset();
        bookingSystem.viewBooking("BID101");
        assertTrue(outContent.toString().contains("Appointment Status: ATTENDED"));
        outContent.reset();
        bookingSystem.changeAppointmentStatus("BID101", "BOOKED");
        assertTrue(outContent.toString().contains("Your appointment has already been marked as attended"));
    }

    @Test
    @DisplayName("Test searching patient by booking ID")
    void testSearchPatientByBookingID() {
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        patient foundPatient = bookingSystem.searchBypatname("BID101");
        assertNotNull(foundPatient);
        assertEquals("John Doe", foundPatient.get_pat_name());
        foundPatient = bookingSystem.searchBypatname("INVALID_ID");
        assertNull(foundPatient);
    }

    @Test
    @DisplayName("Test booking checking")
    void testBookingChecking() {
        assertEquals("empty", bookingSystem.bookingchecking());
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        assertEquals("notempty", bookingSystem.bookingchecking());
    }

    @Test
    @DisplayName("Test is appointment booked")
    void testIsAppointmentBooked() {
        String appointmentKey = "Smith_Week 1: Monday_10:00-11:00";
        assertFalse(bookingSystem.isAppointmentBooked(appointmentKey));
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        assertTrue(bookingSystem.isAppointmentBooked(appointmentKey));
    }

    @Test
    @DisplayName("Test patient registration and searching")
    void testPatientRegistration() {
        assertFalse(bookingSystem.isPatientRegistered("John Doe"));
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        assertTrue(bookingSystem.isPatientRegistered("John Doe"));
        assertNotNull(bookingSystem.findPatientByName("John Doe","9876543210"));
        assertNull(bookingSystem.findPatientByName("Nonexistent Patient","0987654321"));
    }

    @Test
    @DisplayName("Test generate clinic report")
    void testGenerateClinicReport() {
        outContent.reset();
        bookingSystem.generateClinicReport();
        assertTrue(outContent.toString().contains("No appointments available for the report"));
        bookingSystem.bookAppointment(patient1, doctor1, "Week 1: Monday", "10:00-11:00", 101, "Smith", "BID101");
        bookingSystem.bookAppointment(patient2, doctor2, "Week 1: Wednesday", "09:00-10:00", 102, "Brown", "BID102");
        bookingSystem.changeAppointmentStatus("BID101", "ATTENDED");
        outContent.reset();
        bookingSystem.generateClinicReport();
        assertTrue(outContent.toString().contains("CLINIC REPORT - APPOINTMENTS LIST"));
        assertTrue(outContent.toString().contains("PHYSIOTHERAPISTS BY ATTENDED APPOINTMENTS"));
    }
}