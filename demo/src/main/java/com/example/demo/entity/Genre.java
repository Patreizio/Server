
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "genres")
@Data                        // Lombok: genera getter, setter, equals, hashCode e toString
@NoArgsConstructor           // Genera costruttore vuoto
@AllArgsConstructor          // Genera costruttore con tutti i campi
@ToString(exclude = "books") // Evita ricorsione infinita nel toString() a causa della relazione bidirezionale
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {
    
    @Id                                         // Identifica la primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Chiave primaria generata automaticamente dal database (auto-increment)
    private Long id;
    
    @Column(nullable = false, unique = true)
    // Nome del genere, obbligatorio e unico nel database (es: "Fantasy", "Horror")
    private String name;
    
    @Column(length = 500)
    // Breve descrizione del genere, massimo 500 caratteri
    private String description;
    
    @OneToMany(
        mappedBy = "genre",             // Indica che il lato proprietario è nella classe Book (campo "genre")
        cascade = CascadeType.ALL,      // Propaga le operazioni (persist, remove, ecc.) ai libri collegati
        fetch = FetchType.LAZY          // I libri vengono caricati solo quando effettivamente necessari
    )
    @JsonIgnore                        // Impedisce ricorsioni quando si serializzano i libri
    private List<Book> books;           // Lista dei libri che appartengono a questo genere
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setDescription(String description) { this.description = description; }
}

