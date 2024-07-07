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
