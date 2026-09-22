package br.ufsc.biblioteca.model;

import java.time.LocalDate;

public class Reserva {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataReserva;
    private boolean ativa;

    public Reserva(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataReserva = LocalDate.now();
        this.ativa = true;
    }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getDataReserva() { return dataReserva; }
    public boolean isAtiva() { return ativa; }
    public void cancelar() { ativa = false; }

    public boolean expirada() {
        return dataReserva.plusDays(3).isAfter(LocalDate.now());
    }
}
