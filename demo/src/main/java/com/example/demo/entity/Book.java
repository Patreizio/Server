
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data                      // Genera getter, setter, equals, hashCode, toString
@NoArgsConstructor         // Costruttore senza argomenti
@AllArgsConstructor        // Costruttore con tutti gli argomenti
public class Book {

    @Id                                        // Identifica la primary key della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Il database genera automaticamente l'ID (auto-increment)
    private Long id;

    @Column(nullable = false)   // Il titolo è obbligatorio
    private String title;

    @Column(unique = true, nullable = false)  
    // ISBN unico e obbligatorio: non possono esistere due libri con lo stesso ISBN
    private String isbn;

    // Anno di pubblicazione del libro (può essere null)
    private Integer publicationYear;

    @ManyToOne(fetch = FetchType.LAZY)
    // Relazione molti-a-uno con Author:
    // molti libri possono appartenere allo stesso autore
    @JoinColumn(name = "author_id", nullable = false)
    // La colonna "author_id" in tabella books è la FK verso la tabella authors
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    // Relazione molti-a-uno con Genre:
    // molti libri possono appartenere allo stesso genere
    @JoinColumn(name = "genre_id", nullable = false)
    // La colonna "genre_id" rappresenta la foreign key verso Genres
    private Genre genre;
        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getIsbn() { return isbn; }
        public Integer getPublicationYear() { return publicationYear; }
        public Author getAuthor() { return author; }
        public Genre getGenre() { return genre; }
        public void setId(Long id) { this.id = id; }
        public void setTitle(String title) { this.title = title; }
        public void setIsbn(String isbn) { this.isbn = isbn; }
        public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
        public void setAuthor(Author author) { this.author = author; }
        public void setGenre(Genre genre) { this.genre = genre; }
}
