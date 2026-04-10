package edu.eci.dosw.DOSW_Library.controller.dto;

import edu.eci.dosw.DOSW_Library.core.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponseDTO {
    private String bookId;
    private String bookTitle;
    private String userId;
    private String username;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Status status;
}
