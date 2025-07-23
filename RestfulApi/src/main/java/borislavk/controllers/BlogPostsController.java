package borislavk.controllers;

import borislavk.entities.Author;
import borislavk.entities.BlogPost;
import borislavk.payloads.NewBlogPostPayload;
import borislavk.services.AuthorsService;
import borislavk.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {

    @Autowired
    private BlogPostsService blogPostsService;

    @Autowired
    private AuthorsService authorsService;

    // 1. GET /blogPosts
    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostsService.findAll();
    }

    // 2. POST /blogPosts (+ payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@RequestBody NewBlogPostPayload payload) {
        Author author = authorsService.findById(payload.getAuthorId());
        return blogPostsService.saveBlogPost(payload, author);
    }

    // 3. GET /blogPosts/{postId}
    @GetMapping("/{postId}")
    public BlogPost getBlogPostById(@PathVariable UUID postId) {
        return blogPostsService.findById(postId);
    }

    // 4. PUT /blogPosts/{postId} (+ payload)
    @PutMapping("/{postId}")
    public BlogPost updateBlogPost(@RequestBody NewBlogPostPayload payload, @PathVariable UUID postId) {
        Author author = authorsService.findById(payload.getAuthorId());
        return blogPostsService.findByIdAndUpdate(postId, payload, author);
    }

    // 5. DELETE /blogPosts/{postId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogPost(@PathVariable UUID postId) {
        blogPostsService.findByIdAndDelete(postId);
    }
}