package com.pedrolucas.ConsulTech.dto.visita;

import com.pedrolucas.ConsulTech.model.Consultor;
import com.pedrolucas.ConsulTech.model.Empresa;
import com.pedrolucas.ConsulTech.model.Status;

import java.time.LocalDateTime;

public record VisitaAtualizarRequest(
        Consultor consultor,

        Empresa empresa,

        LocalDateTime dataHoraInicio,

        LocalDateTime dataHoraFim,

        Status status,

        String observacoes,

        Double localizacaoCheckInLat,

        Double localizacaoCheckInLong,

        Double localizacaoCheckOutLat,

        Double localizacaoCheckOutLong

) {
}
