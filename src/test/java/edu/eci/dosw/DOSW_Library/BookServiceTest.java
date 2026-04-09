package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.model.Book;
import edu.eci.dosw.DOSW_Library.core.service.BookService;
import edu.eci.dosw.DOSW_Library.core.validator.BookValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(new BookValidator());
    }

    @Test
    void addBook_shouldAddBookSuccessfully() {
        Book book = new Book("B1", "Clean Code", "Robert Martin", 10, 3);
        bookService.addBook(book, 3);
        assertEquals(book, bookService.getBookId("B1"));
    }

    @Test
    void getAllBooks_shouldReturnAllBooks() {
        bookService.addBook(new Book("B1", "Clean Code", "Robert Martin", 10, 3), 3);
        bookService.addBook(new Book("B2", "El Quijote", "Cervantes", 20, 10), 2);
        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
    }



}
