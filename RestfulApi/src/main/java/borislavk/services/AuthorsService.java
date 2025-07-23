package borislavk.services;

import borislavk.entities.Author;
import borislavk.exceptions.NotFoundException;
import borislavk.payloads.NewAuthorPayload;
import borislavk.repository.AuthorRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AuthorsService {

    @Autowired
    private AuthorRep authorRep;

    private List<Author> authorsDB = new ArrayList<>();

    public List<Author> findAll() {
        return this.authorsDB;
    }

    public Author saveAuthor(NewAuthorPayload payload) {
        Author newAuthor = new Author(payload.getNome(), payload.getCognome(), payload.getEmail(), payload.getDataDiNascita());
        this.authorsDB.add(newAuthor);
        log.info("Autore con email " + payload.getEmail() + " salvato correttamente.");
        return newAuthor;
    }

    public Author findById(UUID authorId) {
        return authorRep.findById(authorId).orElseThrow(() -> new NotFoundException("Autore con questo ID" + authorId + "non trovato"));
    }

    public Author findByIdAndUpdate(UUID authorId, NewAuthorPayload payload) {
        Author found = this.findById(authorId);

        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setEmail(payload.getEmail());
        found.setDataDiNascita(payload.getDataDiNascita());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.getNome() + "+" + payload.getCognome());
        return authorRep.save(found);
    }

    public void findByIdAndDelete(UUID authorId) {
        Author found = this.findById(authorId);
        authorRep.delete((found));
    }
}