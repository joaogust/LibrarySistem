package domain.model.livro;

import java.util.ArrayList;
import java.util.UUID;

public class Autor {

    private final UUID id;
    private final String nome;
    private ArrayList<Livro> livros;

    private Autor(String nome) {
        this.id = UUID.randomUUID();
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
