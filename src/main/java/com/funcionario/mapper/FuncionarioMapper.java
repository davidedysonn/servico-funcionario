package com.funcionario.mapper;

import com.funcionario.dtos.FuncionarioDTO;
import com.funcionario.entities.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface FuncionarioMapper {
    @Mapping(target = "pessoa", ignore = true)
    Funcionario toEntity (FuncionarioDTO funcionarioDTO);
    
    @Mapping(source = "pessoa.id", target = "pessoaId")
    FuncionarioDTO toDTO (Funcionario funcionario);
}
