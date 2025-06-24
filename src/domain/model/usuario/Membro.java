package domain.model.usuario;

import java.util.UUID;

public class Membro {

    private final UUID id;
    private final String nome;
    private String telefone;
    private final String CPF;
    private final Endereco endereco;

    public Membro(String nome, String CPF, Endereco endereco, String telefone) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public UUID getId() {
        return id;
    }
}
