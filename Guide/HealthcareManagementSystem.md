# Healthcare Management System

### Step 1: Set Up Your Project

1. **Create a new Java Project**:
   - Open Eclipse.
   - Go to `File -> New -> Java Project`.
   - Enter the project name (`HealthcareManagementSystem`).

2. **Add JUnit to your Project**:
   - Right-click on your project in the Project Explorer.
   - Select `Build Path -> Add Libraries...`.
   - Choose `JUnit` and click `Next`.
   - Select `JUnit 5` and click `Finish`.

### Step 2: Create Your Healthcare Management System Classes

1. **Create a package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter a package name (`com.application.healthcare`).

2. **Create a `Patient` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`Patient`).

```java
package com.application.healthcare;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String diagnosis;

    public Patient(String id, String name, int age, String diagnosis) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
```

3. **Create a `PatientService` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`PatientService`).

```java
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
```

### Step 3: Create Your JUnit Test Classes

1. **Create a test package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter the test package name (`com.application.healthcare.test`).

2. **Create a `PatientTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`PatientTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

3. **Write JUnit tests for `Patient` class**:

```java
package com.application.healthcare.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.healthcare.Patient;

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
```

4. **Create a `PatientServiceTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`PatientServiceTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

5. **Write JUnit tests for `PatientService` class**:

```java
package com.application.healthcare.test;

import static org.junit.jupiter.api.Assertions.*;
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
```

### Step 4: Run Your Tests

1. **Run the tests**:
   - Right-click on the `PatientTest` class.
   - Select `Run As -> JUnit Test`.
   - Repeat the same steps for `PatientServiceTest` class.

### Explanation

- **Patient Class**: This class represents a patient with properties like ID, name, age, and diagnosis. It includes methods to get and set these properties.
- **PatientService Class**: This class manages patient records. It includes methods to add, remove, get, and update patient data.
- **PatientTest Class**: This is your JUnit test class for the `Patient` class. It contains various test methods to verify the behavior of the `Patient` class.
- **PatientServiceTest Class**: This is your JUnit test class for the `PatientService` class. It contains various test methods to verify the behavior of the `PatientService` class.
