package br.com.fiap.bo;

import br.com.fiap.dao.ProfissaoDAO;
import br.com.fiap.model.Profissao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ProfissaoBO {

    @Inject // Injeta o ProfissaoDAO
    private ProfissaoDAO profissaoDAO;

    public ProfissaoBO() {}

    public List<Profissao> listar() throws SQLException {
        return profissaoDAO.listar();
    }
}