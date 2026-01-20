package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
// Gestisce le operazioni CRUD e di ricerca legate ai libri.
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    // Restituisce tutti i libri
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(BookDto::fromEntity)
                .collect(Collectors.toList());
    }

    // Restituisce tutti i libri di un dato autore
    @GetMapping("/author/{authorId}")
    public List<BookDto> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.getBooksByAuthor(authorId)
                .stream()
                .map(BookDto::fromEntity)
                .collect(Collectors.toList());
    }

    // Restituisce un libro per ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(BookDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea un nuovo libro
    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        Book savedBook = bookService.createBook(buildBookEntity(bookDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(BookDto.fromEntity(savedBook));
    }

    // Aggiorna un libro esistente
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book updatedBook = bookService.updateBook(id, buildBookEntity(bookDto));
        return ResponseEntity.ok(BookDto.fromEntity(updatedBook));
    }

    // Elimina un libro per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    private Book buildBookEntity(BookDto bookDto) {
        Author author = resolveAuthor(bookDto.getAuthorId());
        Genre genre = resolveGenre(bookDto.getGenreId());
        return bookDto.toEntity(author, genre);
    }

    private Author resolveAuthor(Long authorId) {
        if (authorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "authorId is required");
        }
        return authorService.getAuthorById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }

    private Genre resolveGenre(Long genreId) {
        if (genreId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "genreId is required");
        }
        return genreService.getGenreById(genreId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
    }
}
