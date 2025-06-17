package domain.model;

import java.util.ArrayList;
import java.util.UUID;

public class Autor {

    private final UUID id;
    private final String nome;
    private ArrayList<Livro> livros;

    private Autor(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", livros=" + livros +
                '}';
    }
}
