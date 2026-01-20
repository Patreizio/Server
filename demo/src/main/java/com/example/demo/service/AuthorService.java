package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Incapsula la logica applicativa relativa agli autori.
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Aggiorna l'autore se presente; in caso contrario crea un nuovo record coerente con l'ID richiesto.
    public Author updateAuthor(Long id, Author authorDetails) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setFirstName(authorDetails.getFirstName());
                    author.setLastName(authorDetails.getLastName());
                    author.setBirthDate(authorDetails.getBirthDate());
                    return authorRepository.save(author);
                })
                .orElseGet(() -> {
                    authorDetails.setId(id);
                    return authorRepository.save(authorDetails);
                });
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
