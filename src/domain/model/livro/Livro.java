package domain.model.livro;

import java.util.ArrayList;
import java.util.UUID;

public class Livro {

    private final UUID id;
    private final String titulo;
    private final String ISBN;
    private final ArrayList<Autor> autores;
    private final ArrayList<Copia> copias;

    public Livro(String titulo, String ISBN, ArrayList<Autor> autores, int qtdCopias) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.autores = autores;
        copias = new ArrayList<>(qtdCopias);

        for (int i = 0; i < qtdCopias; i++) {
            copias.add(new Copia());
        }
    }

    public UUID getId() {
        return id;
    }

    public int totalDisponiveis() {
        int qtd = 0;
        for (Copia i : copias) {
            qtd += i.isStatusCopia() ? 1 : 0;
        }
        return qtd;
    }
}
