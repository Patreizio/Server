
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Data                       // Genera automaticamente getter, setter, equals, hashCode e toString
@NoArgsConstructor          // Genera un costruttore senza argomenti
@AllArgsConstructor         // Genera un costruttore con tutti i campi
@ToString(exclude = "books") // Evita ricorsioni infinite nel toString() escludendo la lista books
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {

    @Id                                         // Identifica la primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID generato automaticamente dal database (auto-increment)
    private Long id;

    @Column(nullable = false)   // Il campo non può essere null nel DB
    private String firstName;

    @Column(nullable = false)   // Anche questo campo è obbligatorio
    private String lastName;

    // Data di nascita dell'autore (può essere null)
    private LocalDate birthDate;

    @OneToMany(
        mappedBy = "author",            // Indica che la relazione è gestita dalla classe Book (campo "author")
        cascade = CascadeType.ALL,      // Ogni operazione sull'autore viene propagata ai libri (persist, delete, ecc.)
        fetch = FetchType.LAZY          // I libri vengono caricati solo quando servono (ottimizzazione)
    )
    @JsonIgnore                        // Evita cicli JSON quando un libro contiene l'autore
    private List<Book> books;           // Lista dei libri scritti dall'autore
        public Long getId() { return id; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public LocalDate getBirthDate() { return birthDate; }
        public void setId(Long id) { this.id = id; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}
