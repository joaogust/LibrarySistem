package domain.model.usuario;

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

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", numero=" + numero +
                ", CEP='" + CEP + '\'' +
                '}';
    }
}
