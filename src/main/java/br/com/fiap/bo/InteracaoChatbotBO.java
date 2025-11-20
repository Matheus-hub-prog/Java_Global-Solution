package br.com.fiap.bo;

import br.com.fiap.dao.InteracaoChatbotDAO;
import br.com.fiap.model.InteracaoChatbot;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class InteracaoChatbotBO {

    @Inject // Injeta o InteracaoChatbotDAO
    private InteracaoChatbotDAO interacaoDAO;

    public InteracaoChatbotBO() {}

    public void gravarInteracao(InteracaoChatbot interacao) throws SQLException {
        if (interacao.getPergunta() == null || interacao.getPergunta().trim().isEmpty()) {
            throw new IllegalArgumentException("A pergunta não pode ser vazia.");
        }
        interacaoDAO.gravarInteracao(interacao);
    }

    public List<InteracaoChatbot> listarHistorico(Long usuarioId) throws SQLException {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        return interacaoDAO.listarPorUsuario(usuarioId);
    }
}