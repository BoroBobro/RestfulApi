package borislavk.services;

import borislavk.entities.Author;
import borislavk.exceptions.NotFoundException;
import borislavk.payloads.NewAuthorPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorsService {

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

    public Author findById(int authorId) {
        for (Author author : this.authorsDB) {
            if (author.getId() == authorId) return author;
        }
        throw new NotFoundException(authorId);
    }

    public Author findByIdAndUpdate(int authorId, NewAuthorPayload payload) {
        Author found = null;
        for (Author author : this.authorsDB) {
            if (author.getId() == authorId) {
                found = author;
                found.setNome(payload.getNome());
                found.setCognome(payload.getCognome());
                found.setEmail(payload.getEmail());
                found.setDataDiNascita(payload.getDataDiNascita());
                found.setAvatar("https://ui-avatars.com/api/?name=" + payload.getNome() + "+" + payload.getCognome());
            }
        }
        if (found == null) throw new NotFoundException(authorId);
        return found;
    }

    public void findByIdAndDelete(int authorId) {
        Author found = null;
        for (Author author : this.authorsDB) {
            if (author.getId() == authorId) found = author;
        }
        if (found == null) throw new NotFoundException(authorId);
        this.authorsDB.remove(found);
    }
}