package domain.model.livro;

import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private ArrayList<Livro> livros;

//    TODO public void salvar(Livro livro) {
//
//    }

//    TODO public Livro buscarPorId(int id) {
//
//    }

    public List<Livro> getLivros(){
        return livros;
    }
}
