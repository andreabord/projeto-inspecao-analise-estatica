package br.ufsc.biblioteca.model;

public class Usuario {
    private String matricula;
    private String nome;
    private String email;
    private int quantidadeEmprestimos;
    private boolean suspenso;

    public Usuario(String matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.quantidadeEmprestimos = 0;
        this.suspenso = false;
    }

    public String getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getQuantidadeEmprestimos() { return quantidadeEmprestimos; }
    public boolean isSuspenso() { return suspenso; }

    public void suspender() { suspenso = true; }
    public void reativar() { suspenso = false; }
    public void registrarEmprestimo() { quantidadeEmprestimos++; }
    public void registrarDevolucao() { quantidadeEmprestimos--; }

    public boolean podeEmprestar() {
        return !suspenso && quantidadeEmprestimos <= 3;
    }

    public boolean emailValido() {
        return email.contains("@");
    }
}
