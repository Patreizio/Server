package com.example.demo.repository;
import com.example.demo.entity.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    // CRUD methods are inherited from JpaRepository:
    // - save(Author author)
    // - findById(Long id)
    // - findAll()
    // - delete(Author author)
    // - deleteById(Long id)
    // - count()
    // - exists(Long id)
    
    // Custom CRUD methods for Author-specific queries
    Optional<Author> findByFirstName(String firstName);

    List<Author> findByFirstNameContainingIgnoreCase(String firstName);
    
    Optional<Author> findByLastName(String lastName);
    
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    
    List<Author> findByFirstNameIgnoreCase(String firstName);
    
    List<Author> findByLastNameIgnoreCase(String lastName);
    
    @Query("SELECT a FROM Author a WHERE UPPER(a.firstName) LIKE UPPER(CONCAT('%', :name, '%')) OR UPPER(a.lastName) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Author> searchByName(@Param("name") String name);
    
    @Query("SELECT COUNT(a) FROM Author a")
    long countTotalAuthors();
}
