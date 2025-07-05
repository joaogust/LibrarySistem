package main.domain.model.emprestimo;

public class AtrasadoState implements EmprestimoState {
    @Override
    public void finalizar(Emprestimo emprestimo) {
        emprestimo.setFinalizado(true);
        emprestimo.setEstado(new FinalizadoState());
    }

    @Override
    public void renovar(Emprestimo emprestimo) {
    }

    @Override
    public boolean estaAtrasado() {
        return true;
    }
}
