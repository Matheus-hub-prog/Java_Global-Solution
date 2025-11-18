package br.com.fiap.resource;

import br.com.fiap.bo.ModuloBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/modulos")
public class ModuloResource {

    private ModuloBO moduloBO;

    public ModuloResource() {
        try {
            this.moduloBO = new ModuloBO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // O Endpoint que retorna os módulos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            return Response.ok(moduloBO.listar()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao listar módulos: " + e.getMessage()).build();
        }
    }
}