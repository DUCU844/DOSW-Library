package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.controller.dto.BookDTO;
import edu.eci.dosw.DOSW_Library.controller.mapper.BookMapper;
import edu.eci.dosw.DOSW_Library.core.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    private final BookMapper mapper = new BookMapper();

    @Test
    void toModel_shouldMapCorrectly() {
        BookDTO dto = new BookDTO("B1", "Titulo", "Autor", 10, 5);

        Book book = mapper.toModel(dto);

        assertEquals("B1", book.getId());
        assertEquals("Titulo", book.getTitle());
        assertEquals("Autor", book.getAuthor());
        assertEquals(10, book.getTotalCopies());
        assertEquals(5, book.getAvailableCopies());
    }

    @Test
    void toDTO_shouldMapCorrectly() {
        Book book = new Book("B1", "Titulo", "Autor", 10, 5);

        BookDTO dto = mapper.toDTO(book);

        assertEquals("B1", dto.getId());
        assertEquals("Titulo", dto.getTitle());
        assertEquals("Autor", dto.getAuthor());
        assertEquals(10, dto.getTotalCopies());
        assertEquals(5, dto.getAvailableCopies());
    }
}
