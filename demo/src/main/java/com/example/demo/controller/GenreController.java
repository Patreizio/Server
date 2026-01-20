package com.example.demo.controller;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.Genre;
import com.example.demo.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
// Centralizza le API dedicate alla catalogazione dei generi letterari.
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Restituisce tutti i generi
    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres()
                .stream()
                .map(GenreDto::fromEntity)
                .collect(Collectors.toList());
    }

    // Restituisce un genere per ID
    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id)
                .map(GenreDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea un nuovo genere
    @PostMapping
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto) {
        Genre savedGenre = genreService.createGenre(genreDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(GenreDto.fromEntity(savedGenre));
    }

    // Aggiorna un genere esistente
    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        Genre updatedGenre = genreService.updateGenre(id, genreDto.toEntity());
        return ResponseEntity.ok(GenreDto.fromEntity(updatedGenre));
    }

    // Elimina un genere per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
