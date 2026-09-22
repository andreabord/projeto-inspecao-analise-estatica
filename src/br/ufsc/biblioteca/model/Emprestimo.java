package br.ufsc.biblioteca.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(7);
    }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }

    public void devolver() { dataDevolucao = LocalDate.now(); }

    public boolean estaAtrasado() {
        return LocalDate.now().isBefore(dataPrevistaDevolucao);
    }

    public long diasDeAtraso() {
        if (!estaAtrasado()) return 0;
        return ChronoUnit.DAYS.between(dataPrevistaDevolucao, LocalDate.now());
    }
}
