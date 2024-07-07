package com.application.healthcare.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.healthcare.Patient;
import com.application.healthcare.PatientService;

class PatientServiceTest {
    private PatientService patientService;
    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setUp() {
        patientService = new PatientService();
        patient1 = new Patient("1", "John Doe", 30, "Flu");
        patient2 = new Patient("2", "Jane Smith", 40, "Cold");
        patientService.addPatient(patient1);
        patientService.addPatient(patient2);
    }

    @Test
    void testAddPatient() {
        Patient patient3 = new Patient("3", "Alice Johnson", 25, "Allergy");
        patientService.addPatient(patient3);
        assertEquals(patient3, patientService.getPatientById("3"));
    }

    @Test
    void testRemovePatient() {
        patientService.removePatient("1");
        assertNull(patientService.getPatientById("1"));
    }

    @Test
    void testGetPatientById() {
        assertEquals(patient1, patientService.getPatientById("1"));
        assertEquals(patient2, patientService.getPatientById("2"));
    }

    @Test
    void testUpdateDiagnosis() {
        patientService.updateDiagnosis("1", "Pneumonia");
        assertEquals("Pneumonia", patientService.getPatientById("1").getDiagnosis());
    }

    @Test
    void testUpdateDiagnosisForNonexistentPatient() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            patientService.updateDiagnosis("3", "Asthma");
        });
        assertEquals("No patient found with ID: 3", exception.getMessage());
    }

    @Test
    void testGetAllPatients() {
        Map<String, Patient> patients = patientService.getAllPatients();
        assertEquals(2, patients.size());
        assertTrue(patients.containsValue(patient1));
        assertTrue(patients.containsValue(patient2));
    }
}
