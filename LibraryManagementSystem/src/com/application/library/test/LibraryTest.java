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