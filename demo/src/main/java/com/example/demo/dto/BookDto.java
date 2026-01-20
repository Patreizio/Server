package com.example.demo.dto;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;

public class BookDto {

    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Long authorId;
    private String authorFullName;
    private Long genreId;
    private String genreName;

    public BookDto() {}

    public BookDto(Long id, String title, String isbn, Integer publicationYear,
                    Long authorId, String authorFullName, Long genreId, String genreName) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.authorId = authorId;
        this.authorFullName = authorFullName;
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public static BookDto fromEntity(Book book) {
        if (book == null) {
            return null;
        }

        Author author = book.getAuthor();
        Genre genre = book.getGenre();

        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setAuthorId(author != null ? author.getId() : null);
        dto.setAuthorFullName(author != null ? author.getFirstName() + " " + author.getLastName() : null);
        dto.setGenreId(genre != null ? genre.getId() : null);
        dto.setGenreName(genre != null ? genre.getName() : null);
        return dto;
    }

    public Book toEntity(Author author, Genre genre) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
