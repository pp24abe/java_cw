import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class bookingTest {

    private booking bookingSystem;
    private Doctor doctor1;
    private Doctor doctor2;
    private patient patient1;

    @BeforeEach
    void setUp() {
        doctor1 = new Doctor("Dr. Smith", "Cardiology", "Clinic A", "1234567890", new ArrayList<>(), "Heart Treatment");
        doctor2 = new Doctor("Dr. Brown", "Neurology", "Clinic B", "0987654321", new ArrayList<>(), "Brain Treatment");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", "Cardiology", "Clinic A", "1234567890", new ArrayList<>(), "Heart Treatment"));
        doctors.add(new Doctor("Dr. Brown", "Neurology", "Clinic B", "0987654321", new ArrayList<>(), "Brain Treatment"));
        bookingSystem = new booking(doctors);

        patient1 = new patient("John Doe", "123 Main St", "9876543210", 453, "BID123");
    }

    @Test
    void testSearchBySpecialization() {
        assertTrue(bookingSystem.searchByspecialization("Cardiology"));
        assertFalse(bookingSystem.searchByspecialization("Dermatology"));
    }

    @Test
    void testSearchByDoctorName() {
        assertTrue(bookingSystem.searchBydocname("Dr. Smith"));
        assertFalse(bookingSystem.searchBydocname("Dr. Nonexistent"));
    }

    @Test
    void testBookAppointment() {
        bookingSystem.bookAppointment(patient1, doctor1, "2025-03-25", "10:00", 567, "Dr. Smith", "BID123");
        assertTrue(bookingSystem.viewBookings("John Doe"));
    }

    @Test
    void testRemoveAppointment() {
        bookingSystem.bookAppointment(patient1, doctor1, "2025-03-25", "10:00", 5675, "Dr. Smith", "BID123");
        bookingSystem.removeAppointment(patient1);
        assertFalse(bookingSystem.viewBookings("John Doe"));
    }

    @Test
    void testChangeAppointmentStatus() {
        bookingSystem.bookAppointment(patient1, doctor1, "2025-03-25", "10:00", 5675, "Dr. Smith", "BID123");
        bookingSystem.changeAppointmentStatus("BID123", "ATTENDED");
        bookingSystem.viewBooking("BID123");
    }

    @Test
    void testGenerateClinicReport() {
        bookingSystem.bookAppointment(patient1, doctor1, "2025-03-25", "10:00", 6757, "Dr. Smith", "BID123");
        bookingSystem.generateClinicReport();
    }
}
