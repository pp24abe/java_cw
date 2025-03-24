import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class patientTest {

    patient patient = new patient("rayan","10 Green St, London","9074466220",4353,"0920381");
    @Test
    void get_pat_name() {
        assertEquals("rayan", patient.get_pat_name());
    }

    @Test
    void get_pat_id() {
        assertEquals(4353, patient.get_pat_id());
    }

    @Test
    void getBiud() {
        assertEquals("0920381", patient.getBiud());
    }

    @Test
    void testEquals() {
        assertTrue(patient.equals(patient));
    }

    @Test
    void testHashCode() {
        System.out.println(patient.hashCode());
        assertEquals(patient.hashCode(), patient.hashCode());
    }
}