package com.funcionario.resources;

import com.funcionario.dtos.FuncionarioDTO;
import com.funcionario.service.FuncionarioService;
import jakarta.annotation.security.RolesAllowed;
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
    @RolesAllowed({"ADMIN", "RH"})
    public Response criar (@Valid FuncionarioDTO funcionarioDTO){
        FuncionarioDTO funcionarioResponse = funcionarioService.criar(funcionarioDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity(funcionarioResponse)
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "RH"})
    public Response atualizar (@PathParam("id") Long id, @Valid FuncionarioDTO funcionarioDTO){
        FuncionarioDTO funcionarioResponse = funcionarioService.atualizar(id, funcionarioDTO);
        return Response
                .status(Response.Status.OK)
                .entity(funcionarioResponse)
                .build();
    }

    @GET
    @RolesAllowed({"ADMIN", "RH"})
    public List<FuncionarioDTO> listFuncionarios (
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size){
        return funcionarioService.listarFuncionarios(page, size);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "RH"})
    public FuncionarioDTO buscarFuncionarioPorId (@PathParam("id") Long id){
        return funcionarioService.buscarPorId(id);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response deletar (@PathParam("id") Long id){
        funcionarioService.removerFuncionario(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
