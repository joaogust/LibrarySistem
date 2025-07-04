package domain.model.emprestimo;

public interface EstadoEmprestimo {
    void finalizar(Emprestimo emprestimo);
    void renovar(Emprestimo emprestimo);
    boolean estaAtrasado();
}
