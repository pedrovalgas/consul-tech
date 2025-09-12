package com.pedrolucas.ConsulTech.dto.consultor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ConsultorCriarRequest(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6)
        String senha,

        @NotBlank
        @Pattern(regexp = "^[0-9]{11}$")
        String telefone,

        @NotBlank
        String cargo
) {
}
