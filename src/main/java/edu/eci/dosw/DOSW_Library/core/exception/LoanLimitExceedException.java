package edu.eci.dosw.DOSW_Library.core.exception;

public class LoanLimitExceedException extends RuntimeException {
    public LoanLimitExceedException(String message) {
        super(message);
    }
}
