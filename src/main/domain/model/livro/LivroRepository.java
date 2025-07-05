package main.domain.model.livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LivroRepository {

    private ArrayList<Livro> livros;

    public LivroRepository() {
        this.livros = new ArrayList<>();

        livros.add(new Livro(
            "Caçadores de Lendas",
            "978-85-333-0227-3",
            3,
            new Autor("Carlos Silva")
        ));
        livros.add(new Livro(
            "A Guerra dos Mundos",
            "978-85-7541-071-5",
            2,
            new Autor("H. G. Wells")
        ));

        livros.add(new Livro(
            "Dom Casmurro",
            "978-85-359-0277-8",
            4,
            new Autor("Machado de Assis")
        ));

        livros.add(new Livro(
            "1984",
            "978-85-359-0278-5",
            5,
            new Autor("George Orwell")
        ));
        livros.add(new Livro(
            "O Pequeno Príncipe",
            "978-85-250-1238-2",
            3,
            new Autor("Antoine de Saint-Exupéry")
        ));


        Collections.sort(livros, Comparator.comparing(Livro::getTitulo));
    }

    public void salvar(Livro livro) {
        livros.add(livro);
        Collections.sort(livros, Comparator.comparing(Livro::getTitulo));
    }

    public Livro buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> getLivros(){
        return livros;
    }
}
