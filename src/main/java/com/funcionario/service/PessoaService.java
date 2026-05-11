package com.funcionario.service;

import com.funcionario.dtos.PessoaDTO;

import java.util.List;

public interface PessoaService {
    PessoaDTO criar (PessoaDTO pessoaDTO);
    PessoaDTO atualizar (Long id, PessoaDTO pessoaDTO);
    PessoaDTO buscarPorId (Long id);
    List<PessoaDTO> listarPessoas ();
    List<PessoaDTO> buscarPessoaNome(String nome);
    void remover (Long id);
}
