package edu.eci.dosw.DOSW_Library.core.service;

import edu.eci.dosw.DOSW_Library.core.model.Book;
import edu.eci.dosw.DOSW_Library.core.model.Loan;
import edu.eci.dosw.DOSW_Library.core.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final Map<Book, Integer> books = new HashMap<>();

    public void addBook(Book book, int copies) {
        books.put(book, copies);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.keySet());
    }

    public Book getBookId(String id){
        return books.keySet().stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
    }

    public void updateBookAvailable(String id, boolean available){
        Book book = getBookId(id);
        book.setAvailable(available);
    }

    public int getCopies(String id) {
        Book book = getBookId(id);
        return books.get(book);
    }

    public void updateCopies(String id, int copies) {
        Book book = getBookId(id);
        books.put(book, copies);
    }

    public void deleteBook(String id) {
        Book book = getBookId(id);
        books.remove(book);
    }

}
