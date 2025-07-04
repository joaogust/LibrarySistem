package domain.model.emprestimo;

import domain.model.livro.Copia;
import domain.model.usuario.Membro;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Emprestimo {
    private final Membro membro;
    private final List<Copia> copias;
    private final LocalDate dataInicial;
    private final LocalDate dataFinal;

    public Emprestimo(Membro membro, List<Copia> copias, LocalDate dataInicial, LocalDate dataFinal) {
        this.membro = membro;
        this.copias = copias;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
    
    public Membro getMembro() {
        return membro;
    }

    public List<Copia> getCopias() {
        return copias;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    @Override
    public String toString() {
        return "Empréstimo de " + membro.getNome() + " com " + copias.size() + " livro(s) de " + dataInicial + " até " + dataFinal;
    }
}
