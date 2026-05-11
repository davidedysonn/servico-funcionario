package com.funcionario.mapper;

import com.funcionario.dtos.PessoaDTO;
import com.funcionario.entities.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PessoaMapper {
    Pessoa toEntity (PessoaDTO pessoaDTO);
    PessoaDTO toDTO (Pessoa pessoa);
}
