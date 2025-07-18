package main.domain.model.emprestimo;

import main.domain.model.livro.Copia;
import main.domain.model.livro.Livro;
import main.domain.model.usuario.Membro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoFactory {

    public static Emprestimo criarEmprestimo(Membro membro, List<Livro> livrosSelecionados, LocalDate dataInicial, LocalDate dataFinal) {
        List<Copia> copiasSelecionadas = new ArrayList<>();

        for (Livro livro : livrosSelecionados) {
            for (Copia copia : livro.getCopias()) {
                if (copia.isStatusCopia()) {
                    copia.marcarComoEmprestada();
                    copiasSelecionadas.add(copia);
                    break;
                }
            }
        }

        if (copiasSelecionadas.size() < livrosSelecionados.size()) {
            throw new RuntimeException("Nem todos os livros possuem cópias disponíveis.");
        }

        Emprestimo emprestimo = new Emprestimo(membro, copiasSelecionadas, dataInicial, dataFinal);

        emprestimo.setEstado(new AbertoState());

        emprestimo.setEstrategiaMulta(new MultaPorDiaStrategy(1.0));

        return emprestimo;
    }
}
