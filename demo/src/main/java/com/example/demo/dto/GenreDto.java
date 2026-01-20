package com.example.demo.dto;

import com.example.demo.entity.Genre;

public class GenreDto {

    private Long id;
    private String name;
    private String description;

    public GenreDto() {}

    public GenreDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static GenreDto fromEntity(Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        return dto;
    }

    public Genre toEntity() {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        genre.setDescription(description);
        return genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
