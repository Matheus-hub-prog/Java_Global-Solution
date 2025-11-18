package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioBO() throws SQLException, ClassNotFoundException {
        this.usuarioDAO = new UsuarioDAO();
    }

    // INSERIR (COM VALIDAÇÕES)
    public void inserir(Usuario usuario) throws SQLException, IllegalArgumentException {
        // Validação 1: Nome obrigatório
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório.");
        }

        // Validação 2: E-mail deve ser válido (ter @ e .)
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        // Validação 3: Senha deve ter pelo menos 6 caracteres
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.");
        }

        // Se passou nas validações, chama o DAO
        usuarioDAO.inserir(usuario);
    }

    // LISTAR
    public List<Usuario> listar() throws SQLException {
        return usuarioDAO.listar();
    }

    // ATUALIZAR
    public void atualizar(Usuario usuario) throws SQLException, IllegalArgumentException {
        // Validação de ID
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório para atualização.");
        }

        // Reutiliza validação de nome (opcional, mas recomendado manter consistência)
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        // Chama o DAO
        usuarioDAO.atualizar(usuario);
    }

    // DELETAR
    public void deletar(Long id) throws SQLException, IllegalArgumentException {
        // Validação de ID
        if (id == null) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }

        // Chama o DAO
        usuarioDAO.deletar(id);
    }
}