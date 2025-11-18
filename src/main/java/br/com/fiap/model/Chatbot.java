package br.com.fiap.model;

public class Chatbot {
    private Long id;
    private String versao;
    private Character status; // A, I
    private String nome;
    private Long plataformaId; // FK

    // Construtores, Getters e Setters
    public Chatbot() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVersao() { return versao; }
    public void setVersao(String versao) { this.versao = versao; }
    public Character getStatus() { return status; }
    public void setStatus(Character status) { this.status = status; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Long getPlataformaId() { return plataformaId; }
    public void setPlataformaId(Long plataformaId) { this.plataformaId = plataformaId; }
}