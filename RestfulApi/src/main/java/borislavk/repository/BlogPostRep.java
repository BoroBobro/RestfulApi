package borislavk.repository;

import borislavk.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogPostRep extends JpaRepository<BlogPost, UUID> {

}
