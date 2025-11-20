package br.com.fiap.resource;

import br.com.fiap.bo.ProfissaoBO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/profissoes")
public class ProfissaoResource {

    @Inject // Injeta o ProfissaoBO
    ProfissaoBO profissaoBO;

    public ProfissaoResource() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            return Response.ok(profissaoBO.listar()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}