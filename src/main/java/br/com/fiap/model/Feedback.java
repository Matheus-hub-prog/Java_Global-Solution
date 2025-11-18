package br.com.fiap.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Feedback {
    private Long id;
    private String descricao;
    private LocalDate data;
    private Integer nota; // CHAR no banco, mas Integer no Java facilita (0-5)
    private Long usuarioId; // FK
    private Long moduloId;  // FK

    public Feedback() {}
    // Getters e Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getModuloId() { return moduloId; }
    public void setModuloId(Long moduloId) { this.moduloId = moduloId; }
}