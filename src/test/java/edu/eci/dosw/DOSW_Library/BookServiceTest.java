package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.model.Book;
import edu.eci.dosw.DOSW_Library.core.service.BookService;
import edu.eci.dosw.DOSW_Library.core.validator.BookValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(new BookValidator());
    }

    @Test
    void addBook_shouldAddSuccessfully() {
        Book book = new Book("B1", "Clean Code", "Martin", 10, 5);

        bookService.addBook(book, 5);

        assertEquals(1, bookService.getAllBooks().size());
    }

    @Test
    void getBookId_shouldReturnBook() {
        Book book = new Book("B1", "Clean Code", "Martin", 10, 5);
        bookService.addBook(book, 5);

        Book result = bookService.getBookId("B1");

        assertEquals("B1", result.getId());
    }

    @Test
    void getBookId_shouldThrowException_whenNotFound() {
        assertThrows(RuntimeException.class, () -> {
            bookService.getBookId("NO_EXISTE");
        });
    }

    @Test
    void updateCopies_shouldUpdateCorrectly() {
        Book book = new Book("B1", "Clean Code", "Martin", 10, 5);
        bookService.addBook(book, 5);

        bookService.updateCopies("B1", 3);

        assertEquals(3, bookService.getCopies("B1"));
    }

    @Test
    void updateBookAvailable_shouldFail_whenIdIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            bookService.updateBookAvailable("", 2);
        });
    }

    @Test
    void updateBookAvailable_shouldUpdateCorrectly() {
        Book book = new Book("B1", "Clean Code", "Martin", 10, 5);
        bookService.addBook(book, 5);

        bookService.updateBookAvailable("B1", 2);

        assertEquals(2, bookService.getBookId("B1").getAvailableCopies());
    }

    @Test
    void deleteBook_shouldRemoveBook() {
        Book book = new Book("B1", "Clean Code", "Martin", 10, 5);
        bookService.addBook(book, 5);

        bookService.deleteBook("B1");

        assertTrue(bookService.getAllBooks().isEmpty());
    }
}