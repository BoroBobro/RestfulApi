package borislavk.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class BlogPost {

    private int id;
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private String cover;
    private Author author; // semplice riferimento

    public BlogPost(String categoria, String titolo, String contenuto, int tempoDiLettura, Author author) {
        Random random = new Random();
        this.id = random.nextInt(1, 10000);
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.cover = "https://picsum.photos/200/300";
        this.author = author;
    }
}
