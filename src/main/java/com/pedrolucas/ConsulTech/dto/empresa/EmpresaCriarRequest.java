package com.pedrolucas.ConsulTech.dto.empresa;

import com.pedrolucas.ConsulTech.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record EmpresaCriarRequest(

        @NotBlank
        String nome,

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
