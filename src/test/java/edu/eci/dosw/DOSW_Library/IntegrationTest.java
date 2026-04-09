package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.model.*;
import edu.eci.dosw.DOSW_Library.core.service.BookService;
import edu.eci.dosw.DOSW_Library.core.service.LoanService;
import edu.eci.dosw.DOSW_Library.core.service.UserService;
import edu.eci.dosw.DOSW_Library.core.validator.BookValidator;
import edu.eci.dosw.DOSW_Library.core.validator.LoanValidator;
import edu.eci.dosw.DOSW_Library.core.validator.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IntegrationTest {

    @Test
    void fullFlow_shouldWorkCorrectly() {

        BookService bookService = new BookService(new BookValidator());
        UserService userService = new UserService(new UserValidator());
        LoanService loanService = new LoanService(
                bookService,
                userService,
                new LoanValidator()
        );

        Book book = new Book("B1", "Clean Code", "Martin", 10, 5);
        User user = new User("U1", "Nombre", "user", "pass", Role.REGULAR_USER);

        bookService.addBook(book, 5);
        userService.addUser(user);

        Loan loan = loanService.createLoan("B1", "U1");

        assertNotNull(loan);
        assertEquals(Status.ACTIVE, loan.getStatus());
    }
}