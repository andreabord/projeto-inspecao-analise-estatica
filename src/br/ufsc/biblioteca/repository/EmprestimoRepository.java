package br.ufsc.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import br.ufsc.biblioteca.model.Emprestimo;
import br.ufsc.biblioteca.model.Usuario;

public class EmprestimoRepository {
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void salvar(Emprestimo emprestimo) { emprestimos.add(emprestimo); }

    public List<Emprestimo> buscarPorUsuario(Usuario usuario) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getMatricula().equals(usuario.getMatricula())) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public List<Emprestimo> listarAtivos() {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getDataDevolucao() != null) resultado.add(e);
        }
        return resultado;
    }

    public List<Emprestimo> listarTodos() { return emprestimos; }
}
