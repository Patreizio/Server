package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Coordina le operazioni di business sui libri prima di accedere al repository.
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Allinea il libro con i nuovi dati, oppure effettua un'inserzione con l'ID specificato.
    public Book updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setIsbn(bookDetails.getIsbn());
                    book.setPublicationYear(bookDetails.getPublicationYear());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setGenre(bookDetails.getGenre());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    bookDetails.setId(id);
                    return bookRepository.save(bookDetails);
                });
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
