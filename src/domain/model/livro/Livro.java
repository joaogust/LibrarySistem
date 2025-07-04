package domain.model.livro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Livro {

    private static int ID = 1;
    private final int id;
    private final String titulo;
    private final String ISBN;
    private final ArrayList<Autor> autores;
    private final ArrayList<Copia> copias;

    public Livro(String titulo, String ISBN, int qtdCopias, Autor... autores) {
        this.id = ID++;
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.autores = new ArrayList<>(Arrays.asList(autores));
        copias = new ArrayList<>(qtdCopias);

        for (int i = 0; i < qtdCopias; i++) {
            copias.add(new Copia());
        }
    }

    public int getId() {
        return id;
    }

    public int totalDisponiveis() {
        int qtd = 0;
        for (Copia i : copias) {
            qtd += i.isStatusCopia() ? 1 : 0;
        }
        return qtd;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo;
    }

    public ArrayList<Copia> getCopias() {
        return copias;
    }

    public static int getID() {
        return ID;
    }

    public String getISBN() {
        return ISBN;
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }
}
