import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    private Doctor doctor;
    private List<Appointment> appointments;

    @BeforeEach
    void setUp() {
        appointments = Arrays.asList(
                new Appointment("Week 1: Monday 5th May 2025", "09:30-12:30", "Dr. Punnoose", "Physiotherapist", 765675, "", "", "", 0),
                new Appointment("Week 2: Tuesday 13th May 2025", "10:00-13:00", "Dr. Punnoose", "Physiotherapist", 675765, "", "", "", 0)
        );

        doctor = new Doctor("Dr. Punnoose", "Physiotherapist", "72 Aviation Avenue", "90744662220", appointments, "Mobilisation");
    }

    @Test
    void testGetName() {
        assertEquals("Dr. Punnoose", doctor.getName());
    }

    @Test
    void testGetSpecialization() {
        assertEquals("Physiotherapist", doctor.getSpecialization());
    }

    @Test
    void testGetAvailableAppointments() {
        assertNotNull(doctor.getAvailableAppointments());
        assertEquals(2, doctor.getAvailableAppointments().size());
    }

    @Test
    void testGetTreatment() {
        assertEquals("Mobilisation", doctor.getTreatment());
    }

    @Test
    void testShowAvailableAppointments() {
        doctor.showAvailableAppointments();

    }
}
