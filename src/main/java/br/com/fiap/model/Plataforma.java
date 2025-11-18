package br.com.fiap.model;

public class Plataforma {
    private Long id;
    private String nome;
    private Character status; // A, I

    // Construtores, Getters e Setters
    public Plataforma() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }
}