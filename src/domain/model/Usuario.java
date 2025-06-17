package domain.model;

import java.util.UUID;

public abstract class Usuario {

    private final UUID id;
    private final String nome;
    private String telefone;

    public Usuario(UUID id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public UUID getId() {
        return id;
    }
}
