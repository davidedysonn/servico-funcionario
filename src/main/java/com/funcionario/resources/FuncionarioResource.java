package com.funcionario.resources;

import com.funcionario.dtos.FuncionarioDTO;
import com.funcionario.service.FuncionarioService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    private final FuncionarioService funcionarioService;

    public FuncionarioResource(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @POST
    public Response criar (@Valid FuncionarioDTO funcionarioDTO){
        FuncionarioDTO funcionarioResponse = funcionarioService.criar(funcionarioDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity(funcionarioResponse)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar (@PathParam("id") Long id, @Valid FuncionarioDTO funcionarioDTO){
        FuncionarioDTO funcionarioResponse = funcionarioService.atualizar(id, funcionarioDTO);
        return Response
                .status(Response.Status.OK)
                .entity(funcionarioResponse)
                .build();
    }

    @GET
    public List<FuncionarioDTO> listFuncionarios (){
        return funcionarioService.listarFuncionarios();
    }

    @GET
    @Path("/{id}")
    public FuncionarioDTO buscarFuncionarioPorId (@PathParam("id") Long id){
        return funcionarioService.buscarPorId(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar (@PathParam("id") Long id){
        funcionarioService.removerFuncionario(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
