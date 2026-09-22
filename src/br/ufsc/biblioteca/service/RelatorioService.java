package br.ufsc.biblioteca.service;

import java.util.List;
import br.ufsc.biblioteca.model.*;
import br.ufsc.biblioteca.repository.*;

public class RelatorioService {
    private LivroRepository livroRepository;
    private EmprestimoRepository emprestimoRepository;

    public RelatorioService(LivroRepository livros, EmprestimoRepository emprestimos) {
        this.livroRepository = livros;
        this.emprestimoRepository = emprestimos;
    }

    public void imprimirResumo() {
        List<Livro> livros = livroRepository.listarTodos();
        List<Emprestimo> emprestimos = emprestimoRepository.listarTodos();

        System.out.println("=== RELATÓRIO DA BIBLIOTECA ===");
        System.out.println("Livros cadastrados: " + livros.size());
        System.out.println("Empréstimos registrados: " + emprestimos.size());

        int disponiveis = 0;
        for (Livro livro : livros) {
            if (livro.isDisponivel()) disponiveis++;
        }
        System.out.println("Livros disponíveis: " + disponiveis);

        int atrasados = 0;
        for (Emprestimo e : emprestimos) {
            if (e.estaAtrasado()) atrasados++;
        }
        System.out.println("Empréstimos atrasados: " + atrasados);
    }

    public double percentualDisponiveis() {
        int disponiveis = 0;
        for (Livro livro : livroRepository.listarTodos()) {
            if (livro.isDisponivel()) disponiveis++;
        }
        return disponiveis / livroRepository.listarTodos().size() * 100;
    }
}
