package domain.model.emprestimo;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public void salvar(Emprestimo e) {
        emprestimos.add(e);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}