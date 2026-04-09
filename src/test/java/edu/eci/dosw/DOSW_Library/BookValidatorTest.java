package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.exception.InvalidBookDataException;
import edu.eci.dosw.DOSW_Library.core.model.Book;
import edu.eci.dosw.DOSW_Library.core.validator.BookValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookValidatorTest {

    @Test
    void validate_shouldPass() {
        Book book = new Book("B1", "Title", "Author", 10, 5);

        assertDoesNotThrow(() -> BookValidator.validate(book));
    }

    @Test
    void validate_shouldFail_whenBookNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            BookValidator.validate(null);
        });
    }

    @Test
    void validate_shouldFail_whenIdBlank() {
        Book book = new Book("", "Title", "Author", 10, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            BookValidator.validate(book);
        });
    }

    @Test
    void validateCopies_shouldFail_whenInvalid() {
        BookValidator validator = new BookValidator();

        assertThrows(IllegalArgumentException.class, () -> {
            validator.validateCopies(0);
        });
    }

    @Test
    void validateStock_shouldFail_whenTotalInvalid() {
        BookValidator validator = new BookValidator();

        assertThrows(InvalidBookDataException.class, () -> {
            validator.validateStock(0, 1);
        });
    }

    @Test
    void validateStock_shouldFail_whenAvailableNegative() {
        BookValidator validator = new BookValidator();

        assertThrows(InvalidBookDataException.class, () -> {
            validator.validateStock(5, -1);
        });
    }

    @Test
    void validateStock_shouldFail_whenAvailableGreaterThanTotal() {
        BookValidator validator = new BookValidator();

        assertThrows(InvalidBookDataException.class, () -> {
            validator.validateStock(5, 10);
        });
    }
}
