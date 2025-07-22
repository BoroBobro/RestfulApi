package borislavk.controllers;

import borislavk.entities.Author;
import borislavk.payloads.NewAuthorPayload;
import borislavk.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorsService authorsService;

    // 1. GET /authors
    @GetMapping
    public List<Author> getAuthors() {
        return authorsService.findAll();
    }

    // 2. POST /authors (+ payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody NewAuthorPayload body) {
        return authorsService.saveAuthor(body);
    }

    // 3. GET /authors/{authorId}
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable int authorId) {
        return authorsService.findById(authorId);
    }

    // 4. PUT /authors/{authorId} (+ payload)
    @PutMapping("/{authorId}")
    public Author updateAuthor(@RequestBody NewAuthorPayload body, @PathVariable int authorId) {
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. DELETE /authors/{authorId}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }
}