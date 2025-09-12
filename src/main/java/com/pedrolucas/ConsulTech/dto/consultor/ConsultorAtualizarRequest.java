package com.pedrolucas.ConsulTech.dto.consultor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ConsultorAtualizarRequest(
        String nome,

        @Email
        String email,

        @Size(min = 6)
        String senha,

        @Pattern(regexp = "^[0-9]{11}$")
        String telefone,

        String cargo

) {
}
