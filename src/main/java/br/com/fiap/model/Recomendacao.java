package br.com.fiap.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Recomendacao {
    private Long id;
    private String motivo;
    private LocalDate data;
    private Long chatbotId; // FK
    private Long usuarioId; // FK
    private Long trilhaId;  // FK (TRILHAS_TRILHA_ID)

    public Recomendacao() {}
    // Getters e Setters...
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getMotivo() { return motivo; }

    public void setMotivo(String motivo) { this.motivo = motivo; }

    public LocalDate getData() { return data; }

    public void setData(LocalDate data) { this.data = data; }

    public Long getChatbotId() { return chatbotId; }

    public void setChatbotId(Long chatbotId) { this.chatbotId = chatbotId; }

    public Long getUsuarioId() { return usuarioId; }

    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getTrilhaId() { return trilhaId; }

    public void setTrilhaId(Long trilhaId) { this.trilhaId = trilhaId; }
}