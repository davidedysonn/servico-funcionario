package com.funcionario.dtos;

import com.funcionario.enums.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(

        @NotBlank(message = "A matrícula é obrigatória")
        @Size(min = 4, max = 4)
        String matricula,
        Boolean status,
        @NotBlank(message = "O turno é obrigatório")
        Turno turno,
        Long pessoaId

) {}
