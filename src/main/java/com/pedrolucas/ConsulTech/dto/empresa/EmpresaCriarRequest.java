package com.pedrolucas.ConsulTech.dto.empresa;

import com.pedrolucas.ConsulTech.model.Endereco;

import java.time.LocalDateTime;

public record EmpresaCriarRequest(

        String nome,

        Endereco endereco,

        Double latitude,

        Double longitude,

        String responsavel,

        String telefoneContato,

        String observacoes


) {
}
