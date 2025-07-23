package borislavk.repository;

import borislavk.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRep extends JpaRepository<Author, UUID> {
    Optional<Author> findByEmail(String email);
}
