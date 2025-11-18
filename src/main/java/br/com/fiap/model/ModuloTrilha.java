package br.com.fiap.model;

public class ModuloTrilha {
    private Long id;
    private Long moduloId; // FK
    private Long trilhaId; // FK

    // Construtores, Getters e Setters
    public ModuloTrilha() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModuloId() {
        return moduloId;
    }

    public void setModuloId(Long moduloId) {
        this.moduloId = moduloId;
    }

    public Long getTrilhaId() {
        return trilhaId;
    }

    public void setTrilhaId(Long trilhaId) {
        this.trilhaId = trilhaId;
    }
}