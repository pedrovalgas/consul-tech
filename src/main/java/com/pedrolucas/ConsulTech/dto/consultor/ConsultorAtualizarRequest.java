package com.pedrolucas.ConsulTech.dto.consultor;

public record ConsultorAtualizarRequest(
        String nome,

        String email,

        String senha,

        String telefone,

        String cargo

) {
}
