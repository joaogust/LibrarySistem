package main.domain.model.livro;

import java.util.ArrayList;
import java.util.Arrays;

public class Livro {

    private static int ID = 1;
    private final int id;
    private String titulo;
    private String ISBN;
    private ArrayList<Autor> autores;
    private ArrayList<Copia> copias;

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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAutores(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    public void setCopias(ArrayList<Copia> copias) {
        this.copias = copias;
    }
}
