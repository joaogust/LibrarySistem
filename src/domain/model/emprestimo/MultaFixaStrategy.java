package domain.model.emprestimo;

public class MultaFixaStrategy implements CalculoMultaStrategy {
    private final double valor;

    public MultaFixaStrategy(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularMulta(Emprestimo emprestimo) {
        return emprestimo.estaAtrasado() ? valor : 0;
    }
}
