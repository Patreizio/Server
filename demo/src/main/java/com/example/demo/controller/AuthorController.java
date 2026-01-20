package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
// Espone gli endpoint REST dedicati alla gestione degli autori.
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Restituisce tutti gli autori
    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .map(AuthorDto::fromEntity)
                .collect(Collectors.toList());
    }

    // Restituisce un autore per ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id)
                .map(AuthorDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea un nuovo autore
    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        Author savedAuthor = authorService.createAuthor(authorDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthorDto.fromEntity(savedAuthor));
    }

    // Aggiorna un autore esistente
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        Author updatedAuthor = authorService.updateAuthor(id, authorDto.toEntity());
        return ResponseEntity.ok(AuthorDto.fromEntity(updatedAuthor));
    }

    // Elimina un autore per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
