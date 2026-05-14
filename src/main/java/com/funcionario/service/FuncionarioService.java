package com.funcionario.service;

import com.funcionario.dtos.FuncionarioDTO;

import java.util.List;

public interface FuncionarioService {
    FuncionarioDTO criar (FuncionarioDTO funcionarioDTO);
    FuncionarioDTO atualizar (Long id, FuncionarioDTO funcionarioDTO);
    FuncionarioDTO buscarPorId (Long id);
    List<FuncionarioDTO> listarFuncionarios (int page, int size);
    void removerFuncionario (Long id);
}
