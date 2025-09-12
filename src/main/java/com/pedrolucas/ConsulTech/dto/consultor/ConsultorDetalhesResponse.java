package com.pedrolucas.ConsulTech.dto.consultor;

import java.time.LocalDateTime;

public record ConsultorDetalhesResponse(
        Long id,

        String nome,

        String email,

        String telefone,

        String cargo,

        LocalDateTime dataCadastro) {
}
