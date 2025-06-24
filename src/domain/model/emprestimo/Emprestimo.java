package domain.model.emprestimo;

import domain.model.livro.Copia;
import domain.model.usuario.Membro;
import domain.model.livro.Livro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Emprestimo {

    private final UUID id;
    private final Membro membro;
    private ArrayList<Copia> livros;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public Emprestimo(Membro membro, ArrayList<Copia> livros, LocalDate dataInicial, LocalDate dataFinal) {
        this.id = UUID.randomUUID();
        this.membro = membro;
        this.livros = livros;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
}
