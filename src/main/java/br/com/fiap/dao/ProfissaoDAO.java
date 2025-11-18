package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Profissao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissaoDAO {

    private Connection conexao;

    public ProfissaoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = ConnectionFactory.getConnection();
    }

    // Método para listar todas as profissões do banco
    public List<Profissao> listar() throws SQLException {
        List<Profissao> lista = new ArrayList<>();
        String sql = "SELECT * FROM GS_PROFISSOES";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Profissao p = new Profissao();
                // Pegando os dados conforme suas colunas no banco
                p.setId(rs.getLong("PROFISSAO_ID"));
                p.setNome(rs.getString("PROFISSAO_NOME"));
                p.setRamo(rs.getString("PROFISSAO_RAMO"));
                p.setSetor(rs.getString("PROFISSAO_SETOR"));

                lista.add(p);
            }
        }
        return lista;
    }
}