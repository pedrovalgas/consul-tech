package com.pedrolucas.ConsulTech.dto.consultor;

import java.time.LocalDateTime;

public record ConsultorCriarRequest(
        String nome,

        String email,

        String senha,

        String telefone,

        String cargo
) {
}
