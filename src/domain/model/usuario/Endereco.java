package domain.model.usuario;

import java.util.UUID;

public class Endereco {

    private final String rua;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final int numero;
    private final String CEP;

    public Endereco(String rua, String bairro, String cidade, String estado, int numero, String CEP) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.CEP = CEP;
    }

    public String getCEP() {
        return CEP;
    }

    public int getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    @Override
    public String toString() {
        return rua + ", n°" + numero + ", " + bairro + ", " + cidade + " - " + estado + " | " + CEP;
    }
}
