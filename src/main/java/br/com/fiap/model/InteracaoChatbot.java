package br.com.fiap.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InteracaoChatbot {
    private Long id;
    private String pergunta;
    private String resposta;
    private LocalDateTime data; // DATE com hora
    private Long chatbotId; // FK
    private Long usuarioId; // FK

    public InteracaoChatbot() {}
    // Getters e Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getPergunta() { return pergunta; }

    public void setPergunta(String pergunta) { this.pergunta = pergunta; }

    public String getResposta() { return resposta; }

    public void setResposta(String resposta) { this.resposta = resposta; }

    public LocalDateTime getData() { return data; }

    public void setData(LocalDateTime data) { this.data = data; }

    public Long getChatbotId() { return chatbotId; }

    public void setChatbotId(Long chatbotId) { this.chatbotId = chatbotId; }

    public Long getUsuarioId() { return usuarioId; }

    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}