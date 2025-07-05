package main.domain.model.emprestimo;

public interface EmprestimoState {
    void finalizar(Emprestimo emprestimo);
    void renovar(Emprestimo emprestimo);
    boolean estaAtrasado();
}
