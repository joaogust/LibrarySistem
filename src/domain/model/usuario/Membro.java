package domain.model.usuario;

import java.util.UUID;

public class Membro {

    private static int ID = 1;
    private final int id;
    private String nome;
    private String telefone;
    private String cpf;
    private Endereco endereco;

    public Membro(String nome, String cpf, Endereco endereco, String telefone) {
        this.id = ID++;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return nome;
    }
}
