package com.example.demo.config;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // Garantisce sempre la presenza di un set base di generi.
            Genre giallo = ensureGenre("Giallo", "Indagini investigative e colpi di scena.");
            Genre horror = ensureGenre("Horror", "Storie di paura, suspense e tensione.");
            Genre fantasy = ensureGenre("Fantasy", "Mondi immaginari ricchi di magia e avventura.");
            Genre romanzo = ensureGenre("Romanzo", "Narrativa generale, storie di vita e sentimenti.");
            Genre altro = ensureGenre("Altro", "Libri che non rientrano in un genere specifico.");

            // Se ci sono già libri, non rigenera i dati demo per evitare duplicati.
            if (bookRepository.count() > 0) {
                return;
            }

            // Definisce due autori da associare ai libri demo.
            Author rowling = createAuthor("J.K.", "Rowling", LocalDate.of(1965, 7, 31));
            Author doyle = createAuthor("Arthur Conan", "Doyle", LocalDate.of(1859, 5, 22));
            authorRepository.saveAll(List.of(rowling, doyle));

            // Costruisce i libri di esempio collegandoli ad autori e generi.
            List<Book> books = List.of(
                    createBook("Harry Potter e la Pietra Filosofale", "9780747532743", 1997, rowling, fantasy),
                    createBook("Harry Potter e la Camera dei Segreti", "9780747538493", 1998, rowling, fantasy),
                    createBook("Il segno dei quattro", "9788804614361", 1890, doyle, giallo)
            );

            bookRepository.saveAll(books);

            // Inserisce ulteriori autori di esempio
            Author author1 = new Author();
            author1.setFirstName("Mario");
            author1.setLastName("Rossi");
            author1.setBirthDate(LocalDate.of(1980, 5, 15));

            Author author2 = new Author();
            author2.setFirstName("Luca");
            author2.setLastName("Bianchi");
            author2.setBirthDate(LocalDate.of(1975, 3, 10));

            Author author3 = new Author();
            author3.setFirstName("Giulia");
            author3.setLastName("Verdi");
            author3.setBirthDate(LocalDate.of(1990, 8, 25));

            authorRepository.save(author1);
            authorRepository.save(author2);
            authorRepository.save(author3);

            System.out.println("Dati di esempio caricati nel database!");
        };
    }

    private Genre createGenre(String name, String description) {
        Genre genre = new Genre();
        genre.setName(name);
        genre.setDescription(description);
        return genre;
    }

    private Genre ensureGenre(String name, String description) {
        return genreRepository.findByName(name)
                .orElseGet(() -> genreRepository.save(createGenre(name, description)));
    }

    private Author createAuthor(String firstName, String lastName, LocalDate birthDate) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthDate(birthDate);
        return author;
    }

    private Book createBook(String title, String isbn, Integer publicationYear, Author author, Genre genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }
}
