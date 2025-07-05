package main.domain.model.emprestimo;

public class FinalizadoState implements EmprestimoState {
    @Override
    public void finalizar(Emprestimo emprestimo) {
    }

    @Override
    public void renovar(Emprestimo emprestimo) {
    }

    @Override
    public boolean estaAtrasado() {
        return false;
    }
}
