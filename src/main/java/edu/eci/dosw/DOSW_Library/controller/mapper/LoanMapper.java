package edu.eci.dosw.DOSW_Library.controller.mapper;

import edu.eci.dosw.DOSW_Library.controller.dto.LoanResponseDTO;
import edu.eci.dosw.DOSW_Library.core.model.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public LoanResponseDTO toDTO(Loan loan) {
        return new LoanResponseDTO(
                loan.getBook().getId(),
                loan.getBook().getTitle(),
                loan.getUser().getId(),
                loan.getUser().getUserName(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getStatus()
        );
    }
}
