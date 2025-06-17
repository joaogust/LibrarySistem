package domain.model;

import java.util.ArrayList;
import java.util.UUID;

public class Livro {

    private final UUID id;
    private final String titulo;
    private final String ISBN;
    private final ArrayList<Autor> autores;
    private final ArrayList<Copia> copias;

    public Livro(UUID id, String titulo, String ISBN, ArrayList<Autor> autores, ArrayList<Copia> copias) {
        this.id = id;
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.autores = autores;
        this.copias = copias;
    }

    public UUID getId() {
        return id;
    }
}
