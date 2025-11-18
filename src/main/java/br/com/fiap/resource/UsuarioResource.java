package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.model.Usuario;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioResource {

    private UsuarioBO usuarioBO;

    // Construtor inicializa a camada de Business Object
    public UsuarioResource() {
        try {
            this.usuarioBO = new UsuarioBO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    // GET - LISTAR TODOS OS USUÁRIOS
    // URL: http://localhost:8080/usuarios
    // ----------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            return Response.ok(usuarioBO.listar()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar usuários: " + e.getMessage()).build();
        }
    }

    // ----------------------------------------------------------
    // POST - CADASTRAR NOVO USUÁRIO
    // URL: http://localhost:8080/usuarios
    // Body: JSON do Usuário
    // ----------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Usuario usuario) {
        try {
            usuarioBO.inserir(usuario);
            // Retorna status 201 (Created) e o objeto criado
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (IllegalArgumentException e) {
            // Erro de validação (ex: email inválido) retorna 400 Bad Request
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro interno: " + e.getMessage()).build();
        }
    }

    // ----------------------------------------------------------
    // PUT - ATUALIZAR USUÁRIO EXISTENTE
    // URL: http://localhost:8080/usuarios/{id}
    // Body: JSON do Usuário com dados novos
    // ----------------------------------------------------------
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Usuario usuario) {
        try {
            usuario.setId(id); // Garante que o ID da URL seja o usado no objeto
            usuarioBO.atualizar(usuario);
            return Response.ok("Usuário atualizado com sucesso!").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar: " + e.getMessage()).build();
        }
    }

    // ----------------------------------------------------------
    // DELETE - REMOVER USUÁRIO
    // URL: http://localhost:8080/usuarios/{id}
    // ----------------------------------------------------------
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            usuarioBO.deletar(id);
            // Retorna 204 (No Content) pois apagou com sucesso e não tem o que devolver
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao deletar: " + e.getMessage()).build();
        }
    }
}