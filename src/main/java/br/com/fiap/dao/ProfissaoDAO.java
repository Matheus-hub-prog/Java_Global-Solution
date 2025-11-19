package br.com.fiap.dao;

import br.com.fiap.model.Profissao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProfissaoDAO {

    @Inject
    DataSource dataSource;

    public ProfissaoDAO() {}

    public List<Profissao> listar() throws SQLException {
        List<Profissao> lista = new ArrayList<>();
        String sql = "SELECT * FROM GS_PROFISSOES";

        try (Connection conexao = dataSource.getConnection(); // NOVO
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Profissao p = new Profissao();
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