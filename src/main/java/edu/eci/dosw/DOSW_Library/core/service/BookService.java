package edu.eci.dosw.DOSW_Library.core.service;

import edu.eci.dosw.DOSW_Library.core.model.Book;
import edu.eci.dosw.DOSW_Library.core.util.ValidationUtil;
import edu.eci.dosw.DOSW_Library.core.validator.BookValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final Map<Book, Integer> books = new HashMap<>();
    private final BookValidator bookValidator;

    public BookService(BookValidator bookValidator) {
        this.bookValidator = bookValidator;
    }

    public void addBook(Book book, int totalCopies) {
        bookValidator.validate(book);
        bookValidator.validateCopies(totalCopies);
        books.put(book, totalCopies);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.keySet());
    }

    public Book getBookId(String id){
        ValidationUtil.validateNotBlank(id, "El identificador no puede ser nulo");
        return books.keySet().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
    }

    public void updateBookAvailable(String id, int availableCopies){
        ValidationUtil.validateNotBlank(id, "El identificador no puede ser nulo");
        Book book = getBookId(id);
        book.setAvailableCopies(availableCopies);
    }

    public int getCopies(String id) {
        ValidationUtil.validateNotBlank(id, "El identificador no puede ser nulo");
        Book book = getBookId(id);
        return books.get(book);
    }

    public void updateCopies(String id, int copies) {
        ValidationUtil.validateNotBlank(id, "El identificador no puede ser nulo");
        ValidationUtil.validatePositive(copies, "El copia no puede ser negativo");
        Book book = getBookId(id);
        books.put(book, copies);
    }

    public void deleteBook(String id) {
        ValidationUtil.validateNotBlank(id, "El identificador no puede ser nulo");
        Book book = books.keySet()
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        books.remove(book);
    }

}
