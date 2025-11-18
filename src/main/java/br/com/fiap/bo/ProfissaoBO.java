package br.com.fiap.bo;

import br.com.fiap.dao.ProfissaoDAO;
import br.com.fiap.model.Profissao;

import java.sql.SQLException;
import java.util.List;

public class ProfissaoBO {

    private ProfissaoDAO profissaoDAO;

    public ProfissaoBO() throws SQLException, ClassNotFoundException {
        this.profissaoDAO = new ProfissaoDAO();
    }

    public List<Profissao> listar() throws SQLException {
        return profissaoDAO.listar();
    }
}