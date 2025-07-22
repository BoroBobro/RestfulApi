package borislavk.services;

import borislavk.entities.Author;
import borislavk.entities.BlogPost;
import borislavk.exceptions.NotFoundException;
import borislavk.payloads.NewBlogPostPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogPostsService {

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

    public BlogPost findById(int blogPostId) {
        for (BlogPost post : this.blogPostsDB) {
            if (post.getId() == blogPostId) return post;
        }
        throw new NotFoundException(blogPostId);
    }

    public BlogPost findByIdAndUpdate(int blogPostId, NewBlogPostPayload payload, Author author) {
        BlogPost found = null;
        for (BlogPost post : this.blogPostsDB) {
            if (post.getId() == blogPostId) {
                found = post;
                found.setCategoria(payload.getCategoria());
                found.setTitolo(payload.getTitolo());
                found.setContenuto(payload.getContenuto());
                found.setTempoDiLettura(payload.getTempoDiLettura());
                found.setAuthor(author);
            }
        }
        if (found == null) throw new NotFoundException(blogPostId);
        return found;
    }

    public void findByIdAndDelete(int blogPostId) {
        BlogPost found = null;
        for (BlogPost post : this.blogPostsDB) {
            if (post.getId() == blogPostId) found = post;
        }
        if (found == null) throw new NotFoundException(blogPostId);
        this.blogPostsDB.remove(found);
    }
}