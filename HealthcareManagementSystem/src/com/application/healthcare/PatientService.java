package com.application.healthcare;

import java.util.HashMap;
import java.util.Map;

public class PatientService {
    private Map<String, Patient> patientRecords;

    public PatientService() {
        this.patientRecords = new HashMap<>();
    }

    public void addPatient(Patient patient) {
        patientRecords.put(patient.getId(), patient);
    }

    public void removePatient(String id) {
        patientRecords.remove(id);
    }

    public Patient getPatientById(String id) {
        return patientRecords.get(id);
    }

    public void updateDiagnosis(String id, String diagnosis) {
        Patient patient = patientRecords.get(id);
        if (patient != null) {
            patient.setDiagnosis(diagnosis);
        } else {
            throw new IllegalArgumentException("No patient found with ID: " + id);
        }
    }

    public Map<String, Patient> getAllPatients() {
        return new HashMap<>(patientRecords);
    }
}