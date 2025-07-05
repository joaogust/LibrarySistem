package main.domain.model.emprestimo;

public class AbertoState implements EmprestimoState {
    @Override
    public void finalizar(Emprestimo emprestimo) {
        emprestimo.setFinalizado(true);
        emprestimo.setEstado(new FinalizadoState());
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
