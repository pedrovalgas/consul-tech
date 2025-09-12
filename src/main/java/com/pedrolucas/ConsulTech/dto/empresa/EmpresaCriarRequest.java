package com.pedrolucas.ConsulTech.dto.empresa;

import com.pedrolucas.ConsulTech.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmpresaCriarRequest(

        @NotBlank
        String nome,

        @NotBlank
        @Valid
        Endereco endereco,

        @NotNull
        Double latitude,

        @NotNull
        Double longitude,

        @NotBlank
        String responsavel,

        @NotBlank
        String telefoneContato,

        String observacoes
) {
}
