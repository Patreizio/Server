package com.example.demo.dto;

import com.example.demo.entity.Author;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) per l'entità Author.
 * Serve per trasferire i dati dell'autore tra i livelli dell'applicazione
 * senza esporre direttamente l'entità del database.
 */
public class AuthorDto {

    /** Identificativo univoco dell'autore */
    private Long id;
    /** Nome dell'autore */
    private String firstName;
    /** Cognome dell'autore */
    private String lastName;
    /** Data di nascita dell'autore */
    private LocalDate birthDate;

    /** Costruttore vuoto richiesto per la deserializzazione */
    public AuthorDto() {}

    /**
     * Costruttore completo
     * @param id identificativo
     * @param firstName nome
     * @param lastName cognome
     * @param birthDate data di nascita
     */
    public AuthorDto(Long id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    /**
     * Converte un'entità Author in AuthorDto.
     * @param author entità da convertire
     * @return DTO corrispondente
     */
    public static AuthorDto fromEntity(Author author) {
        if (author == null) {
            return null;
        }
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setBirthDate(author.getBirthDate());
        return dto;
    }

    /**
     * Converte questo DTO in un'entità Author.
     * @return entità Author
     */
    public Author toEntity() {
        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthDate(birthDate);
        return author;
    }

    // Getter e Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
