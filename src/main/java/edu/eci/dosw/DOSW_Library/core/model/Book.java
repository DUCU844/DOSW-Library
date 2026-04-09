package edu.eci.dosw.DOSW_Library.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String id;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;
}
