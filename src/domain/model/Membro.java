package domain.model;

import java.util.UUID;

public class Membro extends Usuario {

    private final String CPF;
    private final Endereco endereco;

    private Membro(UUID id, String nome, String telefone, String CPF, Endereco endereco) {
        super(id, nome, telefone);
        this.CPF = CPF;
        this.endereco = endereco;
    }
}
