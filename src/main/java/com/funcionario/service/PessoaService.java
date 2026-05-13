package com.funcionario.service;

import com.funcionario.dtos.PessoaDTO;

import java.util.List;

public interface PessoaService {
    PessoaDTO criar (PessoaDTO pessoaDTO);
    PessoaDTO atualizar (Long id, PessoaDTO pessoaDTO);
    PessoaDTO buscarPorId (Long id);
    List<PessoaDTO> listarPessoas (int page, int size);
    List<PessoaDTO> buscarPessoaNome(String nome, int page, int size);
    void remover (Long id);
}
