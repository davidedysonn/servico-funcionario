package com.funcionario.resources;

import com.funcionario.dtos.PessoaDTO;
import com.funcionario.service.PessoaService;
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
    public Response criar (@Valid PessoaDTO pessoaDTO) {
        PessoaDTO pessoaResponse = pessoaService.criar(pessoaDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity(pessoaResponse)
                .build();
    }

    @PUT
    @Path("/{id}")
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
    public PessoaDTO  buscarPorId (@PathParam("id") Long id){
        return pessoaService.buscarPorId(id);
    }

    @GET
    // ps. aqui segue o mesmo padrao do spring se nao botar nada ele vai pro metodo all.
    public List<PessoaDTO> listarPessoas (){
        return pessoaService.listarPessoas();
    }

    @GET
    @Path("/buscar")
    public List<PessoaDTO> buscarPorNome (@QueryParam("nome") String nome) {
        return pessoaService.buscarPessoaNome(nome);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar (@PathParam("id") Long id){
        pessoaService.remover(id);
        return Response.noContent().build();
    }


}
