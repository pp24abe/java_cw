import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    Appointment appointment = new Appointment("Week 2: Tuesday 13th May 2025", "10:00-13:00", "Dr. Punnoose", "Physiotherapist", 0, "", "BOOKED", "massage", 0);

    @Test
    void testGetDate() {
        assertEquals("Week 2: Tuesday 13th May 2025", appointment.getDate());
    }

    @Test
    void testGetTime() {
        assertEquals("10:00-13:00", appointment.getTime());
    }

    @Test
    void testGetDoctor() {
        assertEquals("Dr. Punnoose", appointment.getDoctor());
    }

    @Test
    void testGetSpecialization() {
        assertEquals("Physiotherapist", appointment.getSpecialization());
    }

    @Test
    void testGetUid() {
        assertEquals(0, appointment.getpid());
    }

    @Test
    void testGetBuid() {
        assertEquals("", appointment.getBuid());
    }

    @Test
    void testGetStatus() {
        assertEquals("BOOKED", appointment.getStatus());
    }

    @Test
    void testSetStatus() {
        appointment.setStatus("ATTENDED");
        assertEquals("ATTENDED", appointment.getStatus());
    }

    @Test
    void testGetTreatmentName() {
        assertEquals("massage", appointment.getTreatmentname());
    }

    @Test
    void testGetChanged() {
        assertEquals(0, appointment.getchanged());
    }
}
