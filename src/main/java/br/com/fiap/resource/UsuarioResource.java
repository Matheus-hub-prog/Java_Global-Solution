package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.model.Usuario;

import jakarta.inject.Inject; // NOVO: Injeção CDI
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioResource {

    @Inject // O Quarkus injeta o BO, eliminando 'new UsuarioBO()'
    UsuarioBO usuarioBO;

    public UsuarioResource() {
        // Construtor vazio é suficiente para o CDI
    }

    // GET - LISTAR TODOS OS USUÁRIOS
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            return Response.ok(usuarioBO.listar()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar usuários: " + e.getMessage()).build();
        }
    }

    // POST - CADASTRAR NOVO USUÁRIO
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Usuario usuario) {
        try {
            usuarioBO.inserir(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro interno: " + e.getMessage()).build();
        }
    }

    // PUT - ATUALIZAR USUÁRIO
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") Long id, Usuario usuario) {
        try {
            usuario.setId(id);
            usuarioBO.atualizar(usuario);
            return Response.ok("Usuário atualizado com sucesso!").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao atualizar: " + e.getMessage()).build();
        }
    }

    // DELETE - REMOVER USUÁRIO
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            usuarioBO.deletar(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao deletar: " + e.getMessage()).build();
        }
    }
}