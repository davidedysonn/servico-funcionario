package com.funcionario.mapper;

import com.funcionario.dtos.PessoaDTO;
import com.funcionario.entities.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface PessoaMapper {
    @Mapping(target = "funcionarios", ignore = true)
    Pessoa toEntity (PessoaDTO pessoaDTO);
    PessoaDTO toDTO (Pessoa pessoa);
}
