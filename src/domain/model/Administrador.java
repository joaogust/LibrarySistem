package domain.model;

import java.util.UUID;

public class Administrador extends Usuario {

    private String email;
    private String senha;

    private Administrador(UUID id, String nome, String telefone, String email, String senha) {
        super(id, nome, telefone);
        this.email = email;
        this.senha = senha;
    }
}
