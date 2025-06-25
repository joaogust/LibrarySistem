package domain.model.usuario;

import java.util.UUID;

public class Membro {

    private static int ID = 1;
    private final int id;
    private final String nome;
    private String telefone;
    private final String cpf;
    private final Endereco endereco;

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

    @Override
    public String toString() {
        return "Membro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
