# Banking System

### Step 1: Set Up Your Project

1. **Create a new Java Project**:
   - Open Eclipse.
   - Go to `File -> New -> Java Project`.
   - Enter the project name (`BankingSystem`).

2. **Add JUnit to your Project**:
   - Right-click on your project in the Project Explorer.
   - Select `Build Path -> Add Libraries...`.
   - Choose `JUnit` and click `Next`.
   - Select `JUnit 5` and click `Finish`.

### Step 2: Create Your Banking System Classes

1. **Create a package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter a package name (`com.application.banking`).

2. **Create a BankAccount class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`BankAccount`).

```java
package com.application.banking;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdraw amount");
        }
    }
}
```

### Step 3: Create Your JUnit Test Class

1. **Create a test package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter the test package name (`com.application.banking.test`).

2. **Create a test class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`BankAccountTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

3. **Write JUnit tests**:

```java
package com.example.banking.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.banking.BankAccount;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(100.0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testDeposit() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testWithdraw() {
        account.withdraw(30.0);
        assertEquals(70.0, account.getBalance());
    }

    @Test
    void testWithdrawInvalidAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150.0);
        });
        assertEquals("Invalid withdraw amount", exception.getMessage());
    }
}
```

### Step 4: Run Your Tests

1. **Run the tests**:
   - Right-click on the `BankAccountTest` class.
   - Select `Run As -> JUnit Test`.

### Explanation

- **BankAccount Class**: This class contains basic functionalities for a bank account, including `deposit`, `withdraw`, and `getBalance` methods.
- **BankAccountTest Class**: This is your JUnit test class. It contains various test methods to verify the behavior of the `BankAccount` class.
  - `@BeforeEach`: This method sets up the initial state before each test.
  - `@Test`: These methods are individual test cases. Each tests a specific behavior of the `BankAccount` class.
