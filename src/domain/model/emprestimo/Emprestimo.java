package domain.model.emprestimo;

import domain.model.livro.Copia;
import domain.model.usuario.Membro;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Emprestimo {
    private final Membro membro;
    private final List<Copia> copias;
    private final LocalDate dataInicial;
    private LocalDate dataFinal;
    private boolean finalizado = false;
    private EstadoEmprestimo estado;
    private CalculoMultaStrategy estrategiaMulta;

    public Emprestimo(Membro membro, List<Copia> copias, LocalDate dataInicial, LocalDate dataFinal) {
        this.membro = membro;
        this.copias = copias;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.estado = new EstadoAberto();
        this.estrategiaMulta = null;
    }

    public Membro getMembro() {
        return membro;
    }

    public List<Copia> getCopias() {
        return copias;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate novaDataFinal) {
        this.dataFinal = novaDataFinal;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public void setEstado(EstadoEmprestimo novoEstado) {
        this.estado = novoEstado;
    }

    public EstadoEmprestimo getEstado() {
        return estado;
    }

    public void setEstrategiaMulta(CalculoMultaStrategy estrategia) {
        this.estrategiaMulta = estrategia;
    }

    public double calcularMulta() {
        if (estado != null && estrategiaMulta != null) {
            return estrategiaMulta.calcularMulta(this);
        }
        return 0.0;
    }

    public void finalizar() {
        estado.finalizar(this);
        if (finalizado) {
            for (Copia copia : copias) {
                copia.marcarComoDisponivel();
            }
        }
    }

    public void renovar() {
        estado.renovar(this);
    }

    public boolean estaAtrasado() {
        if (!finalizado && LocalDate.now().isAfter(dataFinal)) {
            setEstado(new EstadoAtrasado());
        }
        return estado.estaAtrasado();
    }

    @Override
    public String toString() {
        return "Empréstimo de " + membro.getNome() + " com " + copias.size() + " livro(s) de " + dataInicial + " até " + dataFinal;
    }
}
