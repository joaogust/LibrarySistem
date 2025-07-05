package main.domain.model.livro;

public class Autor {
    private final String nome;

    public Autor(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
