package domain.model.emprestimo;

public class EstadoAtrasado implements EstadoEmprestimo {
    @Override
    public void finalizar(Emprestimo emprestimo) {
        emprestimo.setFinalizado(true);
        emprestimo.setEstado(new EstadoFinalizado());
    }

    @Override
    public void renovar(Emprestimo emprestimo) {
    }

    @Override
    public boolean estaAtrasado() {
        return true;
    }
}
