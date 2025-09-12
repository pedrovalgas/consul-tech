package com.pedrolucas.ConsulTech.dto.empresa;

import com.pedrolucas.ConsulTech.model.Endereco;

public record EmpresaAtualizarRequest(
        String nome,

        Endereco endereco,

        Double latitude,

        Double longitude,

        String responsavel,

        String telefoneContato,

        String observacoes
) {
}
