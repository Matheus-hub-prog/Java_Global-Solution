package br.com.fiap.bo;

import br.com.fiap.dao.InteracaoChatbotDAO; // NOVO IMPORT
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UsuarioBO {

    @Inject
    UsuarioDAO usuarioDAO;

    @Inject
    InteracaoChatbotDAO interacaoDAO;

    public UsuarioBO() {}

    // INSERIR (COM VALIDAÇÕES)
    public void inserir(Usuario usuario) throws SQLException, IllegalArgumentException {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.");
        }
        usuarioDAO.inserir(usuario);
    }

    // LISTAR
    public List<Usuario> listar() throws SQLException {
        return usuarioDAO.listar();
    }

    // ATUALIZAR
    public void atualizar(Usuario usuario) throws SQLException, IllegalArgumentException {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório para atualização.");
        }
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        usuarioDAO.atualizar(usuario);
    }

    // DELETAR
    public void deletar(Long id) throws SQLException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }

        // 1. EXCLUI OS REGISTROS FILHOS PRIMEIRO (Interações do Chatbot)
        // Isso evita o erro ORA-02292 (restrição de integridade)
        interacaoDAO.deletarPorUsuario(id);

        // 2. EXCLUI O REGISTRO PAI (o usuário)
        usuarioDAO.deletar(id);
    }
}