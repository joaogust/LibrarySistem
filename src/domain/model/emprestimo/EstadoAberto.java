package domain.model.emprestimo;

public class EstadoAberto implements EstadoEmprestimo {
    @Override
    public void finalizar(Emprestimo emprestimo) {
        emprestimo.setFinalizado(true);
        emprestimo.setEstado(new EstadoFinalizado());
    }

    @Override
    public void renovar(Emprestimo emprestimo) {
        emprestimo.setDataFinal(emprestimo.getDataFinal().plusDays(7)); // exemplo
    }

    @Override
    public boolean estaAtrasado() {
        return false;
    }
}
