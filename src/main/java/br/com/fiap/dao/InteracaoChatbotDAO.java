package br.com.fiap.dao;

import br.com.fiap.model.InteracaoChatbot;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InteracaoChatbotDAO {

    @Inject
    DataSource dataSource;

    public InteracaoChatbotDAO() {}

    // INSERT (Gravar a conversa)
    public void gravarInteracao(InteracaoChatbot interacao) throws SQLException {
        String sql = "INSERT INTO GS_INTERACOES_CHATBOT (INTERACAO_PERGUNTA, INTERACAO_RESPOSTA, INTERACAO_DATA, CHATBOT_ID, USUARIO_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = dataSource.getConnection(); // NOVO
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, interacao.getPergunta());
            stmt.setString(2, interacao.getResposta());
            stmt.setTimestamp(3, Timestamp.valueOf(interacao.getData()));
            stmt.setLong(4, interacao.getChatbotId());
            stmt.setLong(5, interacao.getUsuarioId());

            stmt.execute();
        }
    }

    // LISTAR POR USUARIO (Histórico)
    public List<InteracaoChatbot> listarPorUsuario(Long usuarioId) throws SQLException {
        List<InteracaoChatbot> historico = new ArrayList<>();
        String sql = "SELECT * FROM GS_INTERACOES_CHATBOT WHERE USUARIO_ID = ? ORDER BY INTERACAO_DATA DESC";

        try (Connection conexao = dataSource.getConnection(); // NOVO
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    InteracaoChatbot i = new InteracaoChatbot();
                    i.setId(rs.getLong("INTERACAO_ID"));
                    i.setPergunta(rs.getString("INTERACAO_PERGUNTA"));
                    i.setResposta(rs.getString("INTERACAO_RESPOSTA"));
                    i.setData(rs.getTimestamp("INTERACAO_DATA").toLocalDateTime());
                    i.setChatbotId(rs.getLong("CHATBOT_ID"));
                    i.setUsuarioId(rs.getLong("USUARIO_ID"));
                    historico.add(i);
                }
            }
        }
        return historico;
    }
}