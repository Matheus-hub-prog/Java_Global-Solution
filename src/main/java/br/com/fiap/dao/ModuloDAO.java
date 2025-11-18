package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Modulo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAO {

    private Connection conexao;

    public ModuloDAO() throws SQLException, ClassNotFoundException {
        this.conexao = ConnectionFactory.getConnection();
    }

    public List<Modulo> listar() throws SQLException {
        List<Modulo> modulos = new ArrayList<>();
        String sql = "SELECT * FROM GS_MODULOS ORDER BY MODULO_ID ASC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Modulo m = new Modulo();
                m.setId(rs.getLong("MODULO_ID"));
                m.setTitulo(rs.getString("MODULO_TITULO"));
                m.setConteudo(rs.getString("MODULO_CONTEUDO"));
                m.setDuracao(rs.getInt("MODULO_DURACAO"));
                modulos.add(m);
            }
        }
        return modulos;
    }
}