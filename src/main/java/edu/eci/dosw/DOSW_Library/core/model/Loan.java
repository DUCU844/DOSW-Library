package edu.eci.dosw.DOSW_Library.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private Book book;
    private User user;
    private Date loanDate;
    private Status status; //"ACTIVE", "RETURNED"
    private Date returnDate;
}
