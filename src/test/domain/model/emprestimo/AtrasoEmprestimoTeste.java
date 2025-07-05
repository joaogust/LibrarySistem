package test.domain.model.emprestimo;

import main.domain.model.emprestimo.Emprestimo;
import main.domain.model.emprestimo.EmprestimoFactory;
import main.domain.model.livro.Autor;
import main.domain.model.livro.Livro;
import main.domain.model.usuario.Endereco;
import main.domain.model.usuario.Membro;

import java.time.LocalDate;
import java.util.List;

public class AtrasoEmprestimoTeste {

    public static void main(String[] args) {
        Autor autor = new Autor("Maria Lins");
        Livro livro = new Livro("Engenharia de Software", "11111", 1, autor);

        Endereco endereco = new Endereco("Rua C", "Bairro X", "CidadeY", "EstadoZ", 300, "45678-000");
        Membro membro = new Membro("Beatriz", "321.654.987-00", endereco, "77777-7777");

        // Simula empréstimo vencido (7 dias atrás)
        LocalDate inicio = LocalDate.now().minusDays(14);
        LocalDate fim = LocalDate.now().minusDays(7);

        Emprestimo emprestimo = EmprestimoFactory.criarEmprestimo(
                membro, List.of(livro), inicio, fim
        );

        if (emprestimo.estaAtrasado()) {
            System.out.println("Teste 3 OK: empréstimo corretamente identificado como atrasado.");
        } else {
            System.out.println("Teste 3 Falhou: empréstimo não identificado como atrasado.");
        }
    }
}
