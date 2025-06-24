package domain.model.usuario;

import java.util.UUID;

public class Membro {

    private final UUID id;
    private final String nome;
    private String telefone;
    private final String CPF;
    private final Endereco endereco;

    public Membro(UUID id, String nome, String CPF, Endereco endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public UUID getId() {
        return id;
    }
}
