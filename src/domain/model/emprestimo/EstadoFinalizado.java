package domain.model.emprestimo;

public class EstadoFinalizado implements EstadoEmprestimo {
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
