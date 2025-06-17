package domain.model;

import java.util.ArrayList;
import java.util.UUID;

public class Emprestimo {

    private final UUID id;
    private final Membro membro;
    private ArrayList<Livro> livros;

    private Emprestimo(UUID id, Membro membro) {
        this.id = id;
        this.membro = membro;
        this.livros = new ArrayList<>();
    }
}
