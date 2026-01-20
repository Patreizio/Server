package com.example.demo.service;

import com.example.demo.entity.Genre;
import com.example.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Fornisce un punto unico per la logica relativa ai generi letterari.
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Applica i nuovi dati al genere esistente oppure crea un nuovo record con l'ID richiesto.
    public Genre updateGenre(Long id, Genre genreDetails) {
        return genreRepository.findById(id)
                .map(genre -> {
                    genre.setName(genreDetails.getName());
                    genre.setDescription(genreDetails.getDescription());
                    return genreRepository.save(genre);
                })
                .orElseGet(() -> {
                    genreDetails.setId(id);
                    return genreRepository.save(genreDetails);
                });
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
