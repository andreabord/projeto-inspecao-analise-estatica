package br.ufsc.biblioteca.app;

import br.ufsc.biblioteca.model.*;
import br.ufsc.biblioteca.repository.*;
import br.ufsc.biblioteca.service.*;

public class Main {
    public static void main(String[] args) {
        LivroRepository livros = new LivroRepository();
        EmprestimoRepository emprestimos = new EmprestimoRepository();

        livros.salvar(new Livro("978-01", "Engenharia de Software", "Ian Sommerville", 2019));
        livros.salvar(new Livro("978-02", "Código Limpo", "Robert Martin", 2009));
        livros.salvar(new Livro("978-03", "Estruturas de Dados", "Ana Silva", 2023));

        Usuario usuario = new Usuario("20260001", "Maria", "maria@universidade.br");

        EmprestimoService es = new EmprestimoService(livros, emprestimos);
        ReservaService rs = new ReservaService(livros);
        RelatorioService rel = new RelatorioService(livros, emprestimos);

        es.emprestar("978-01", usuario);
        es.emprestar("978-02", usuario);
        rs.reservar("978-01", usuario);

        double multa = es.devolver("978-01", usuario);
        if (multa > 0) System.out.println("Multa: R$ " + multa);

        rel.imprimirResumo();
        System.out.println("Percentual disponível: " + rel.percentualDisponiveis() + "%");
    }
}
