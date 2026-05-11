package com.funcionario.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
        String cpf

) {}
