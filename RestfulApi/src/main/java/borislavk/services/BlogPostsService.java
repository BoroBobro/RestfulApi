package borislavk.services;

import borislavk.entities.Author;
import borislavk.entities.BlogPost;
import borislavk.exceptions.NotFoundException;
import borislavk.payloads.NewBlogPostPayload;
import borislavk.repository.BlogPostRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BlogPostsService {

    @Autowired
    private BlogPostRep blogPostRep;

    private List<BlogPost> blogPostsDB = new ArrayList<>();

    public List<BlogPost> findAll() {
        return this.blogPostsDB;
    }

    public BlogPost saveBlogPost(NewBlogPostPayload payload, Author author) {
        BlogPost newPost = new BlogPost(
                payload.getCategoria(),
                payload.getTitolo(),
                payload.getContenuto(),
                payload.getTempoDiLettura(),
                author
        );
        this.blogPostsDB.add(newPost);
        log.info("BlogPost con titolo '" + payload.getTitolo() + "' salvato correttamente.");
        return newPost;
    }

    public BlogPost findById(UUID blogPostId) {
        return blogPostRep.findById(blogPostId).orElseThrow(() -> new NotFoundException("Post con ID" + blogPostId + "non trovato!"));
    }

    public BlogPost findByIdAndUpdate(UUID blogPostId, NewBlogPostPayload payload, Author author) {
        BlogPost found = this.findById(blogPostId);

        found.setCategoria(payload.getCategoria());
        found.setTitolo(payload.getTitolo());
        found.setContenuto(payload.getContenuto());
        found.setTempoDiLettura(payload.getTempoDiLettura());
        found.setAuthor(author);
        return blogPostRep.save(found);
    }

    public void findByIdAndDelete(UUID blogPostId) {
        BlogPost found = this.findById(blogPostId);
        blogPostRep.delete(found);
    }
}