package test.domain.model.emprestimo;

import main.domain.model.emprestimo.Emprestimo;
import main.domain.model.emprestimo.EmprestimoFactory;
import main.domain.model.livro.Autor;
import main.domain.model.livro.Livro;
import main.domain.model.usuario.Endereco;
import main.domain.model.usuario.Membro;

import java.time.LocalDate;
import java.util.List;

public class CopiaEmprestadaTeste {

    public static void main(String[] args) {
        Autor autor = new Autor("João Paulo");
        Livro livro = new Livro("Algoritmos", "55555", 1, autor);

        Endereco endereco = new Endereco("Rua B", "Bairro Y", "CidadeZ", "EstadoW", 200, "98765-432");
        Membro membro = new Membro("Carlos", "987.654.321-00", endereco, "88888-8888");

        Emprestimo emprestimo = EmprestimoFactory.criarEmprestimo(
                membro, List.of(livro), LocalDate.now(), LocalDate.now().plusDays(7)
        );

        boolean disponivel = emprestimo.getCopias().get(0).isStatusCopia();

        if (!disponivel) {
            System.out.println("Teste 2 OK: cópia corretamente marcada como emprestada.");
        } else {
            System.out.println("Teste 2 Falhou: cópia ainda está como disponível.");
        }
    }
}
