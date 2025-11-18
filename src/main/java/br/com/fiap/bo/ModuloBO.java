package br.com.fiap.bo;

import br.com.fiap.dao.ModuloDAO;
import br.com.fiap.model.Modulo;
import java.sql.SQLException;
import java.util.List;

public class ModuloBO {

    private ModuloDAO moduloDAO;

    public ModuloBO() throws SQLException, ClassNotFoundException {
        this.moduloDAO = new ModuloDAO();
    }

    public List<Modulo> listar() throws SQLException {
        // Nenhuma regra de negócio aplicada, apenas repassa a listagem.
        return moduloDAO.listar();
    }
}