package br.com.fiap.dao;

import br.com.fiap.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioDAO {

    @Inject
    DataSource dataSource;

    public UsuarioDAO() {}

    // ----------------------------------------------------------
    // C - CREATE (INSERIR)
    // ----------------------------------------------------------
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO GS_USUARIOS (USUARIO_NOME, USUARIO_CPF, USUARIO_EMAIL, USUARIO_SENHA, USUARIO_PROGRESSO, PROFISSAO_ID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = dataSource.getConnection(); // NOVO
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setDouble(5, usuario.getProgresso() != null ? usuario.getProgresso() : 0.0);
            stmt.setLong(6, usuario.getProfissaoId());
            stmt.execute();
        }
    }

    // ----------------------------------------------------------
    // R - READ (LISTAR)
    // ----------------------------------------------------------
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM GS_USUARIOS ORDER BY USUARIO_ID ASC";

        try (Connection conexao = dataSource.getConnection(); // NOVO
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("USUARIO_ID"));
                u.setNome(rs.getString("USUARIO_NOME"));
                u.setCpf(rs.getString("USUARIO_CPF"));
                u.setEmail(rs.getString("USUARIO_EMAIL"));
                u.setSenha(rs.getString("USUARIO_SENHA"));
                u.setProgresso(rs.getDouble("USUARIO_PROGRESSO"));
                u.setProfissaoId(rs.getLong("PROFISSAO_ID"));
                usuarios.add(u);
            }
        }
        return usuarios;
    }

    // ----------------------------------------------------------
    // U - UPDATE (ATUALIZAR)
    // ----------------------------------------------------------
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE GS_USUARIOS SET USUARIO_NOME=?, USUARIO_CPF=?, USUARIO_EMAIL=?, USUARIO_SENHA=?, USUARIO_PROGRESSO=?, PROFISSAO_ID=? WHERE USUARIO_ID=?";

        try (Connection conexao = dataSource.getConnection(); // NOVO
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setDouble(5, usuario.getProgresso() != null ? usuario.getProgresso() : 0.0);
            stmt.setLong(6, usuario.getProfissaoId());
            stmt.setLong(7, usuario.getId());
            stmt.execute();
        }
    }

    // ----------------------------------------------------------
    // D - DELETE (DELETAR)
    // ----------------------------------------------------------
    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM GS_USUARIOS WHERE USUARIO_ID = ?";

        try (Connection conexao = dataSource.getConnection(); // NOVO
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }
}