package com.funcionario.mapper;

import com.funcionario.dtos.FuncionarioDTO;
import com.funcionario.entities.Funcionario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface FuncionarioMapper {
    Funcionario toEntity (FuncionarioDTO funcionarioDTO);
    FuncionarioDTO toDTO (Funcionario funcionario);
}
