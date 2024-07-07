# Library Management System

### Step 1: Set Up Your Project

1. **Create a new Java Project**:
   - Open Eclipse.
   - Go to `File -> New -> Java Project`.
   - Enter the project name (`LibraryManagementSystem`).

2. **Add JUnit to your Project**:
   - Right-click on your project in the Project Explorer.
   - Select `Build Path -> Add Libraries...`.
   - Choose `JUnit` and click `Next`.
   - Select `JUnit 5` and click `Finish`.

### Step 2: Create Your Library Management System Classes

1. **Create a package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter a package name (`com.application.library`).

2. **Create a `Book` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (`Book`).

```java
package com.application.library;

public class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            throw new IllegalStateException("Book is already borrowed");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
        } else {
            throw new IllegalStateException("Book is already returned");
        }
    }
}
```

3. **Create a `Library` class**:
   - Right-click on the package you just created.
   - Select `New -> Class`.
   - Enter the class name (e.g., `Library`).

```java
package com.application.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
```

### Step 3: Create Your JUnit Test Classes

1. **Create a test package**:
   - Right-click on `src`.
   - Select `New -> Package`.
   - Enter the test package name (`com.application.library.test`).

2. **Create a `BookTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`BookTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

3. **Write JUnit tests for `Book` class**:

```java
package com.application.library.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.library.Book;

class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Effective Java", "Joshua Bloch");
    }

    @Test
    void testInitialAvailability() {
        assertTrue(book.isAvailable());
    }

    @Test
    void testBorrowBook() {
        book.borrowBook();
        assertFalse(book.isAvailable());
    }

    @Test
    void testReturnBook() {
        book.borrowBook();
        book.returnBook();
        assertTrue(book.isAvailable());
    }

    @Test
    void testBorrowAlreadyBorrowedBook() {
        book.borrowBook();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            book.borrowBook();
        });
        assertEquals("Book is already borrowed", exception.getMessage());
    }

    @Test
    void testReturnAlreadyReturnedBook() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            book.returnBook();
        });
        assertEquals("Book is already returned", exception.getMessage());
    }
}
```

4. **Create a `LibraryTest` class**:
   - Right-click on the test package.
   - Select `New -> JUnit Test Case`.
   - Enter the test class name (`LibraryTest`).
   - Ensure `JUnit 5` is selected and click `Finish`.

5. **Write JUnit tests for `Library` class**:

```java
package com.application.library.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.application.library.Book;
import com.application.library.Library;

class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("Effective Java", "Joshua Bloch");
        book2 = new Book("Clean Code", "Robert C. Martin");
        library.addBook(book1);
        library.addBook(book2);
    }

    @Test
    void testAddBook() {
        Book book3 = new Book("Design Patterns", "Erich Gamma");
        library.addBook(book3);
        assertEquals(3, library.getBooks().size());
        assertTrue(library.getBooks().contains(book3));
    }

    @Test
    void testRemoveBook() {
        library.removeBook(book1);
        assertEquals(1, library.getBooks().size());
        assertFalse(library.getBooks().contains(book1));
    }

    @Test
    void testFindBookByTitle() {
        Book foundBook = library.findBookByTitle("Effective Java");
        assertNotNull(foundBook);
        assertEquals("Effective Java", foundBook.getTitle());
    }

    @Test
    void testFindBookByTitleNotFound() {
        Book foundBook = library.findBookByTitle("Nonexistent Book");
        assertNull(foundBook);
    }
}
```

### Step 4: Run Your Tests

1. **Run the tests**:
   - Right-click on the `BookTest` class.
   - Select `Run As -> JUnit Test`.
   - Repeat the same steps for `LibraryTest` class.

### Explanation

- **Book Class**: This class represents a book with properties like title, author, and availability status. It includes methods to borrow and return the book.
- **Library Class**: This class manages a collection of books. It includes methods to add, remove, and find books.
- **BookTest Class**: This is your JUnit test class for the `Book` class. It contains various test methods to verify the behavior of the `Book` class.
- **LibraryTest Class**: This is your JUnit test class for the `Library` class. It contains various test methods to verify the behavior of the `Library` class.
