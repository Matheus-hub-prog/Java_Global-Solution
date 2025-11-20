package br.com.fiap.bo;

import br.com.fiap.dao.ModuloDAO;
import br.com.fiap.model.Modulo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ModuloBO {

    @Inject // Injeta o ModuloDAO
    private ModuloDAO moduloDAO;

    public ModuloBO() {}

    public List<Modulo> listar() throws SQLException {
        return moduloDAO.listar();
    }
}