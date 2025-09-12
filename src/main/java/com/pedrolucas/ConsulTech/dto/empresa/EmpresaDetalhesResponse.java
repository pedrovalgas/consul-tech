package com.pedrolucas.ConsulTech.dto.empresa;

import com.pedrolucas.ConsulTech.model.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import java.time.LocalDateTime;

public record EmpresaDetalhesResponse(

        Long id,

        String nome,

        Endereco endereco,

        Double latitude,

        Double longitude,

        String responsavel,

        String telefoneContato,

        String observacoes,

        LocalDateTime dataCadastro
) {
}
