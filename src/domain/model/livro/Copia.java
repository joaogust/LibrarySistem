package domain.model.livro;

import java.util.UUID;

public class Copia {

    private final UUID id;
    private boolean statusCopia;

    private Copia(UUID id) {
        this.id = id;
        this.statusCopia = true;
    }

    public String marcarComoDisponivel() {
        statusCopia = true;
        if (statusCopia) {
            return "A cópia já estava disponível.";
        }
        return "A cópia foi devolvida com sucesso!";
    }

    public String marcarComoEmprestada() {
        statusCopia = false;
        if (statusCopia) {
            return "A cópia foi emprestada com sucesso!";
        }
        return "A cópia já estava indisponível.";
    }

    public boolean isStatusCopia() {
        return statusCopia;
    }

    public UUID getId() {
        return id;
    }
}
