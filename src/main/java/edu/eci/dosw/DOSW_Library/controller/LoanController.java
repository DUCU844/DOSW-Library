package edu.eci.dosw.DOSW_Library.controller;



import edu.eci.dosw.DOSW_Library.core.model.Loan;
import edu.eci.dosw.DOSW_Library.core.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Loans", description = "Operaciones para gestión de préstamos")
@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(summary = "Crear un préstamo", description = "Crea un nuevo préstamo verificando disponibilidad del libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente"),
            @ApiResponse(responseCode = "409", description = "No hay copias disponibles del libro"),
            @ApiResponse(responseCode = "404", description = "Libro o usuario no encontrado")
    })
    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestParam String bookId,@RequestParam String userId) {
        return ResponseEntity.status(201).body(loanService.createLoan(bookId,userId));
    }

    @Operation(summary = "Devolver un libro", description = "Registra la devolución de un libro prestado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro devuelto exitosamente"),
            @ApiResponse(responseCode = "500", description = "Préstamo activo no encontrado")
    })
    @PutMapping("/return")
    public ResponseEntity<Loan> returnBook(@RequestParam String bookId,
                                           @RequestParam String userId) {
        return ResponseEntity.ok(loanService.returnBook(bookId, userId));
    }

    @Operation(summary = "Obtener todos los préstamos", description = "Retorna la lista completa de préstamos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de préstamos retornada exitosamente")
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @Operation(summary = "Obtener préstamos por usuario", description = "Retorna todos los préstamos asociados a un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamos del usuario retornados exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUser(@PathVariable String userId) {
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }

    @Operation(summary = "Obtener préstamos por libro", description = "Retorna todos los préstamos asociados a un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamos del libro retornados exitosamente"),
            @ApiResponse(responseCode = "500", description = "Libro no encontrado")
    })
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Loan>> getLoanByBook (@PathVariable String bookId){
        return ResponseEntity.ok(loanService.getLoansByBook(bookId));
    }

    @Operation(summary = "Expirar un préstamo", description = "Cambia el estado de un préstamo activo a expirado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Préstamo expirado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Préstamo activo no encontrado")
    })
    @PutMapping("/expire")
    public ResponseEntity<Loan> expireLoan(@RequestParam String bookId, @RequestParam String userId){
        return ResponseEntity.ok(loanService.expireLoan(bookId, userId));
    }
}
