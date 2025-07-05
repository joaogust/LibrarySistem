package main.domain.model.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MembroRepository {
    private ArrayList<Membro> membros;

    public MembroRepository() {
        this.membros = new ArrayList<>();

        membros.add(new Membro("João", "123.456.789-10", new Endereco("rua 1", "bairro 1", "cidade 1", "SP", 123, "12345-23"), "(11)99999-9999"));
        membros.add(new Membro("Andrey", "321.543.432-23", new Endereco("rua 2", "bairro 2", "cidade 2", "RJ", 3132, "54321-22"), "(11)98888-8888"));
        membros.add(new Membro("Elton", "789.654.132-34", new Endereco("rua 3", "bairro 3", "cidade 3", "PE", 1323, "09876-11"), "(11)97777-7777"));
        membros.add(new Membro("Rafael", "986.534.274-43", new Endereco("rua 4", "bairro 4", "cidade 4", "RN", 321, "25819-43"), "(11)96666-6666"));
        membros.add(new Membro("Vinícius", "554.345.151-65", new Endereco("rua 5", "bairro 5", "cidade 5", "MG", 659, "90821-09"), "(11)95555-5555"));

        Collections.sort(membros, Comparator.comparing(Membro::getNome));

    }

    public ArrayList<Membro> getMembros() {
        return membros;
    }

    public Membro buscarPorId(int id) {
        for (Membro m : membros) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public void adicionarMembroOrdenado(Membro novoMembro) {
        int pos = Collections.binarySearch(membros, novoMembro, Comparator.comparing(Membro::getNome));
        if (pos < 0) {
            pos = -(pos + 1);
        }
        membros.add(pos, novoMembro);
    }

}
