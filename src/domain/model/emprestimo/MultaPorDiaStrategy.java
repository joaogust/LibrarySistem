package domain.model.emprestimo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MultaPorDiaStrategy implements CalculoMultaStrategy {
    private final double valorPorDia;

    public MultaPorDiaStrategy(double valorPorDia) {
        this.valorPorDia = valorPorDia;
    }

    @Override
    public double calcularMulta(Emprestimo emprestimo) {
        if (!emprestimo.estaAtrasado()) return 0;
        long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataFinal(), LocalDate.now());
        return diasAtraso * valorPorDia;
    }
}
