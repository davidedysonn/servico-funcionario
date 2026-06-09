package com.funcionario.resources;

import com.funcionario.dtos.PessoaDTO;
import com.funcionario.service.PessoaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {
    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @POST
    @RolesAllowed({"ADMIN", "RH"})
    public Response criar (@Valid PessoaDTO pessoaDTO) {
        PessoaDTO pessoaResponse = pessoaService.criar(pessoaDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity(pessoaResponse)
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "RH"})
    public Response atualizar (@PathParam("id") Long id, @Valid PessoaDTO pessoaDTO){
        PessoaDTO pessoaResponse = pessoaService.atualizar(id, pessoaDTO);
        return Response
                .status(Response.Status.OK)
                .entity(pessoaResponse)
                .build();

    }

    /* ps. o quarkus por padrao infere que pra esse tipo de metodo ja devolve um Ok
    * isso ira valer tanto para busca quanto para o put caso eu nao
    * fizesse uso do response.
    * */
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "RH"})
    public PessoaDTO  buscarPorId (@PathParam("id") Long id){
        return pessoaService.buscarPorId(id);
    }

    @GET
    @RolesAllowed({"ADMIN", "RH"})
    // ps. aqui segue o mesmo padrao do spring se nao botar nada ele vai pro metodo all.
    public List<PessoaDTO> listarPessoas (
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size){
        return pessoaService.listarPessoas(page, size);
    }

    @GET
    @Path("/buscar")
    @RolesAllowed({"ADMIN", "RH"})
    public List<PessoaDTO> buscarPorNome (
            @QueryParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size){

        return pessoaService.buscarPessoaNome(nome, page, size );
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response deletar (@PathParam("id") Long id){
        pessoaService.remover(id);
        return Response.noContent().build();
    }


}
