package borislavk.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@NoArgsConstructor

@Getter
@Setter
@ToString
public class Author {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogPost> blogPostList;

    public Author(String nome, String cognome, String email, LocalDate dataDiNascita) {
        Random random = new Random();
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }
}
