package br.ufsc.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import br.ufsc.biblioteca.model.Livro;

public class LivroRepository {
    private List<Livro> livros = new ArrayList<>();

    public void salvar(Livro livro) { livros.add(livro); }

    public Livro buscarPorIsbn(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn() == isbn) return livro;
        }
        return null;
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    public List<Livro> listarTodos() { return livros; }

    public void remover(String isbn) {
        Livro encontrado = buscarPorIsbn(isbn);
        livros.remove(encontrado);
    }
}
