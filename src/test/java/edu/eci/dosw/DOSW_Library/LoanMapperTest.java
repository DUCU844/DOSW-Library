package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.controller.dto.LoanResponseDTO;
import edu.eci.dosw.DOSW_Library.controller.mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.core.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanMapperTest {

    private final LoanMapper mapper = new LoanMapper();

    @Test
    void toDTO_shouldMapCorrectly() {
        Book book = new Book("B1", "Titulo", "Autor", 10, 5);
        User user = new User("U1", "Nombre", "user", "pass", Role.REGULAR_USER);

        Loan loan = new Loan(
                book,
                user,
                LocalDate.now(),
                Status.ACTIVE,
                null
        );

        LoanResponseDTO dto = mapper.toDTO(loan);

        assertEquals("B1", dto.getBookId());
        assertEquals("Titulo", dto.getBookTitle());
        assertEquals("U1", dto.getUserId());
        assertEquals("user", dto.getUsername());
        assertEquals(Status.ACTIVE, dto.getStatus());
    }
}
