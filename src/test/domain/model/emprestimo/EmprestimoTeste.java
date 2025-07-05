package test.domain.model.emprestimo;

import main.domain.model.emprestimo.Emprestimo;
import main.domain.model.emprestimo.EmprestimoFactory;
import main.domain.model.livro.Livro;
import main.domain.model.usuario.Endereco;
import main.domain.model.usuario.Membro;
import main.domain.model.livro.Autor;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoTeste {

    public static void main(String[] args) {
        Autor autor = new Autor("José da Silva");
        Livro livro = new Livro("Java Básico", "12345", 1, autor);
        Endereco endereco = new Endereco("Rua A", "Centro", "CidadeX", "EstadoY", 100, "12345-678");
        Membro membro = new Membro("Ana", "123.456.789-00", endereco, "99999-9999");

        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucao = hoje.plusDays(7);

        Emprestimo emprestimo = EmprestimoFactory.criarEmprestimo(
                membro, List.of(livro), hoje, dataDevolucao
        );

        if (emprestimo.isFinalizado()) {
            System.out.println("Erro: empréstimo já deveria estar em aberto.");
        } else {
            System.out.println("Empréstimo criado com sucesso, está aberto.");
        }

        emprestimo.finalizar();

        if (emprestimo.isFinalizado() && emprestimo.getCopias().get(0).isStatusCopia()) {
            System.out.println("Teste 1 OK: empréstimo finalizado e cópia disponível.");
        } else {
            System.out.println("Teste 1 Falhou.");
        }
    }
}
