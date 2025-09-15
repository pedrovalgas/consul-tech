package com.pedrolucas.ConsulTech.dto.visita;

import com.pedrolucas.ConsulTech.model.Status;

import java.time.LocalDateTime;

public record VisitaAtualizarRequest(
        Long consultorId,

        Long empresaId,

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
