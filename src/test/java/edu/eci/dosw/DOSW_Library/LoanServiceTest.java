package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.core.exception.BookNotAvailableException;
import edu.eci.dosw.DOSW_Library.core.model.*;
import edu.eci.dosw.DOSW_Library.core.service.BookService;
import edu.eci.dosw.DOSW_Library.core.service.LoanService;
import edu.eci.dosw.DOSW_Library.core.service.UserService;
import edu.eci.dosw.DOSW_Library.core.validator.LoanValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    private LoanService loanService;
    private BookService bookService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        bookService = mock(BookService.class);
        userService = mock(UserService.class);

        loanService = new LoanService(
                bookService,
                userService,
                new LoanValidator()
        );
    }

    private Book createBook(String id) {
        return new Book(id, "Clean", "Author", 10, 5);
    }

    private User createUser(String id) {
        return new User(id, "Nombre", "user", "pass", Role.REGULAR_USER);
    }

    @Test
    void getAllLoans_shouldReturnLoans() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");

        assertEquals(1, loanService.getAllLoans().size());
    }

    @Test
    void getLoansByUser_shouldReturnLoans() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");

        assertEquals(1, loanService.getLoansByUser("U1").size());
    }

    @Test
    void getLoansByUser_shouldReturnEmptyList() {
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));

        assertTrue(loanService.getLoansByUser("U1").isEmpty());
    }

    @Test
    void getLoansByBook_shouldReturnLoans() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");

        assertEquals(1, loanService.getLoansByBook("B1").size());
    }

    @Test
    void getLoansByBook_shouldReturnEmptyList() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));

        assertTrue(loanService.getLoansByBook("B1").isEmpty());
    }

    @Test
    void createLoan_shouldCreateSuccessfully() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        Loan loan = loanService.createLoan("B1", "U1");

        assertEquals(Status.ACTIVE, loan.getStatus());
        verify(bookService).updateCopies("B1", 4);
    }

    @Test
    void createLoan_shouldThrowException_whenNoCopies() {
        when(bookService.getCopies("B1")).thenReturn(0);

        assertThrows(BookNotAvailableException.class, () -> {
            loanService.createLoan("B1", "U1");
        });
    }

    @Test
    void returnBook_shouldReturnSuccessfully() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(4);

        loanService.createLoan("B1", "U1");

        Loan returned = loanService.returnBook("B1", "U1");

        assertEquals(Status.RETURNED, returned.getStatus());
    }

    @Test
    void expireLoan_shouldThrowException_whenLoanNotFound() {
        assertThrows(RuntimeException.class, () -> {
            loanService.expireLoan("B1", "U1");
        });
    }

    @Test
    void expireLoan_shouldFail_whenBookDoesNotMatch() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");

        assertThrows(RuntimeException.class, () -> {
            loanService.expireLoan("B2", "U1");
        });
    }

    @Test
    void expireLoan_shouldFail_whenUserDoesNotMatch() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");

        assertThrows(RuntimeException.class, () -> {
            loanService.expireLoan("B1", "U2");
        });
    }

    @Test
    void expireLoan_shouldFail_whenLoanAlreadyReturned() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");
        loanService.returnBook("B1", "U1");

        assertThrows(RuntimeException.class, () -> {
            loanService.expireLoan("B1", "U1");
        });
    }

    @Test
    void returnBook_shouldFail_whenAlreadyReturned() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");
        loanService.returnBook("B1", "U1");

        assertThrows(RuntimeException.class, () -> {
            loanService.returnBook("B1", "U1");
        });
    }

    @Test
    void expireLoan_shouldSkipNonMatchingLoans_andStillFail() {
        Book book1 = createBook("B1");
        Book book2 = createBook("B2");

        User user1 = createUser("U1");
        User user2 = createUser("U2");

        when(bookService.getBookId("B1")).thenReturn(book1);
        when(bookService.getBookId("B2")).thenReturn(book2);
        when(userService.getUserById("U1")).thenReturn(user1);
        when(userService.getUserById("U2")).thenReturn(user2);

        when(bookService.getCopies(anyString())).thenReturn(5);

        loanService.createLoan("B1", "U1");
        loanService.createLoan("B2", "U1");
        loanService.createLoan("B1", "U2");

        assertThrows(RuntimeException.class, () -> {
            loanService.expireLoan("B2", "U2");
        });
    }

    @Test
    void expireLoan_shouldNotMatch_whenStatusIsNotActive_evenIfIdsMatch() {
        when(bookService.getBookId("B1")).thenReturn(createBook("B1"));
        when(userService.getUserById("U1")).thenReturn(createUser("U1"));
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");
        loanService.returnBook("B1", "U1");

        assertThrows(RuntimeException.class, () -> {
            loanService.expireLoan("B1", "U1");
        });
    }

    @Test
    void expireLoan_shouldWorkSuccessfully() {
        Book book = createBook("B1");
        User user = createUser("U1");

        when(bookService.getBookId("B1")).thenReturn(book);
        when(userService.getUserById("U1")).thenReturn(user);
        when(bookService.getCopies("B1")).thenReturn(5);

        loanService.createLoan("B1", "U1");

        Loan loan = loanService.expireLoan("B1", "U1");

        assertEquals(Status.EXPIRED, loan.getStatus());
    }

}
