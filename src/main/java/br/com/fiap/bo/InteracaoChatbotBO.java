package br.com.fiap.bo;

import br.com.fiap.dao.InteracaoChatbotDAO;
import br.com.fiap.model.InteracaoChatbot;

import java.sql.SQLException;
import java.util.List;

public class InteracaoChatbotBO {

    private InteracaoChatbotDAO interacaoDAO;

    public InteracaoChatbotBO() throws SQLException, ClassNotFoundException {
        this.interacaoDAO = new InteracaoChatbotDAO();
    }

    public void gravarInteracao(InteracaoChatbot interacao) throws SQLException {
        // Validação: Não gravar se a pergunta ou resposta estiverem vazias
        if (interacao.getPergunta() == null || interacao.getPergunta().trim().isEmpty()) {
            throw new IllegalArgumentException("A pergunta não pode ser vazia.");
        }

        // Chama o DAO para gravar
        interacaoDAO.gravarInteracao(interacao);
    }

    public List<InteracaoChatbot> listarHistorico(Long usuarioId) throws SQLException {
        // Validação simples de ID
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }
        return interacaoDAO.listarPorUsuario(usuarioId);
    }
}