package edu.eci.dosw.DOSW_Library.core.validator;

import edu.eci.dosw.DOSW_Library.core.exception.BookNotAvailableException;
import edu.eci.dosw.DOSW_Library.core.exception.LoanAlreadyReturnedException;
import edu.eci.dosw.DOSW_Library.core.model.Loan;
import edu.eci.dosw.DOSW_Library.core.model.Status;
import edu.eci.dosw.DOSW_Library.core.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class LoanValidator {
    public void validateIds(String bookId, String userId) {
        ValidationUtil.validateNotBlank(bookId, "El ID del libro no puede estar vacío");
        ValidationUtil.validateNotBlank(userId, "El ID del usuario no puede estar vacío");
    }

    public void validateBookAvailable(int availableCopies) {
        if (availableCopies <= 0) {
            throw new BookNotAvailableException("No hay copias disponibles para el libro solicitado");
        }
    }

    public void validateActiveLoan(Loan loan){
        if (loan.getStatus() == Status.RETURNED){
            throw new LoanAlreadyReturnedException("El prestamo del libro ya fue devuelto anteriormente");
        }
    }
}
