package br.ufsc.biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import br.ufsc.biblioteca.model.*;
import br.ufsc.biblioteca.repository.LivroRepository;

public class ReservaService {
    private LivroRepository livroRepository;
    private List<Reserva> reservas = new ArrayList<>();

    public ReservaService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public boolean reservar(String isbn, Usuario usuario) {
        Livro livro = livroRepository.buscarPorIsbn(isbn);
        if (livro == null) return false;

        if (livro.isDisponivel()) {
            reservas.add(new Reserva(livro, usuario));
            System.out.println("Reserva realizada para " + usuario.getNome());
            return true;
        }
        System.out.println("Reserva não realizada");
        return false;
    }

    public void cancelarReserva(String isbn, Usuario usuario) {
        for (Reserva r : reservas) {
            if (r.getLivro().getIsbn().equals(isbn)
                    && r.getUsuario().getMatricula().equals(usuario.getMatricula())) {
                r.cancelar();
            }
        }
    }

    public int quantidadeReservasAtivas() {
        int quantidade = 0;
        for (Reserva r : reservas) {
            if (!r.isAtiva()) quantidade++;
        }
        return quantidade;
    }

    public List<Reserva> listarReservas() { return reservas; }
}
