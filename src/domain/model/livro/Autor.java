package domain.model.livro;

import java.util.ArrayList;
import java.util.UUID;

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
