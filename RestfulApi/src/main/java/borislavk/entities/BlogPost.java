package borislavk.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;
import java.util.UUID;

@Entity
@NoArgsConstructor

@Getter
@Setter
@ToString
public class BlogPost {
    @Id
    @GeneratedValue
    private UUID id;

    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private String cover;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public BlogPost(String categoria, String titolo, String contenuto, int tempoDiLettura, Author author) {
        Random random = new Random();

        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.cover = "https://picsum.photos/200/300";
        this.author = author;
    }
}
