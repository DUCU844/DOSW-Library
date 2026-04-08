package edu.eci.dosw.DOSW_Library.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    @NotBlank(message = "El ID del libro es obligatorio")
    private String bookId;

    @NotBlank(message = "El ID del usuario es obligatorio")
    private String userId;
}
