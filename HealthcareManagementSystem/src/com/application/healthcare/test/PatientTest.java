package com.application.healthcare.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.healthcare.Patient;

class PatientTest {
    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient("1", "John Doe", 30, "Flu");
    }

    @Test
    void testPatientDetails() {
        assertEquals("1", patient.getId());
        assertEquals("John Doe", patient.getName());
        assertEquals(30, patient.getAge());
        assertEquals("Flu", patient.getDiagnosis());
    }

    @Test
    void testUpdateDiagnosis() {
        patient.setDiagnosis("Cold");
        assertEquals("Cold", patient.getDiagnosis());
    }
}
