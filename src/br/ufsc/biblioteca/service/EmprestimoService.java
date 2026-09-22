package br.ufsc.biblioteca.service;

import br.ufsc.biblioteca.model.*;
import br.ufsc.biblioteca.repository.*;

public class EmprestimoService {
    private LivroRepository livroRepository;
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoService(LivroRepository livros, EmprestimoRepository emprestimos) {
        this.livroRepository = livros;
        this.emprestimoRepository = emprestimos;
    }

    public boolean emprestar(String isbn, Usuario usuario) {
        Livro livro = livroRepository.buscarPorIsbn(isbn);

        if (livro == null) return false;
        if (!livro.isDisponivel()) return false;
        if (usuario.isSuspenso()) return false;
        if (usuario.getQuantidadeEmprestimos() > 3) return false;

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        emprestimoRepository.salvar(emprestimo);
        livro.emprestar();
        usuario.registrarEmprestimo();

        System.out.println("Empréstimo realizado para " + usuario.getNome());
        return true;
    }

    public double devolver(String isbn, Usuario usuario) {
        for (Emprestimo e : emprestimoRepository.buscarPorUsuario(usuario)) {
            if (e.getLivro().getIsbn().equals(isbn) && e.getDataDevolucao() == null) {
                e.devolver();
                e.getLivro().devolver();
                usuario.registrarDevolucao();

                long dias = e.diasDeAtraso();
                if (dias > 0) return dias * 2.50;
                return 0;
            }
        }
        return -1;
    }
}
