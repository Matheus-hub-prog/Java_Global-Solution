package br.com.fiap.resource;

import br.com.fiap.bo.InteracaoChatbotBO;
import br.com.fiap.model.InteracaoChatbot;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/chat")
public class InteracaoChatbotResource {

    private InteracaoChatbotBO interacaoBO;

    public InteracaoChatbotResource() {
        try {
            this.interacaoBO = new InteracaoChatbotBO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response gravarInteracao(InteracaoChatbot interacao) {
        try {
            interacaoBO.gravarInteracao(interacao);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao gravar interação").build();
        }
    }

    @GET
    @Path("/{usuarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarHistorico(@PathParam("usuarioId") Long usuarioId) {
        try {
            return Response.ok(interacaoBO.listarHistorico(usuarioId)).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao buscar histórico").build();
        }
    }
}