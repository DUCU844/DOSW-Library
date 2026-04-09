package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.exception.BookNotAvailableException;
import edu.eci.dosw.DOSW_Library.core.exception.LoanAlreadyReturnedException;
import edu.eci.dosw.DOSW_Library.core.model.*;
import edu.eci.dosw.DOSW_Library.core.validator.LoanValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoanValidatorTest {

    private LoanValidator validator = new LoanValidator();

    @Test
    void validateIds_shouldFail_whenBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.validateIds("", "U1");
        });
    }

    @Test
    void validateBookAvailable_shouldFail() {
        assertThrows(BookNotAvailableException.class, () -> {
            validator.validateBookAvailable(0);
        });
    }

    @Test
    void validateActiveLoan_shouldFail_whenReturned() {
        Loan loan = new Loan(
                new Book("B1","T","A",10,5),
                new User("U1","N","u","p", Role.REGULAR_USER),
                null,
                Status.RETURNED,
                null
        );

        assertThrows(LoanAlreadyReturnedException.class, () -> {
            validator.validateActiveLoan(loan);
        });
    }

    @Test
    void validateBookAvailable_shouldPass_whenCopiesPositive() {
        LoanValidator validator = new LoanValidator();

        assertDoesNotThrow(() -> {
            validator.validateBookAvailable(5);
        });
    }

    @Test
    void validateActiveLoan_shouldPass_whenLoanIsActive() {
        LoanValidator validator = new LoanValidator();

        Loan loan = new Loan(
                new Book("B1","T","A",10,5),
                new User("U1","N","u","p",Role.REGULAR_USER),
                null,
                Status.ACTIVE,
                null
        );

        assertDoesNotThrow(() -> {
            validator.validateActiveLoan(loan);
        });
    }

}
