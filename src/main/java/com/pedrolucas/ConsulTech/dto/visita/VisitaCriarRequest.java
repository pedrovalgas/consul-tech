package com.pedrolucas.ConsulTech.dto.visita;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedrolucas.ConsulTech.model.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record VisitaCriarRequest(

        @NotNull
        Long consultorId,

        @NotNull
        Long empresaId,

        @NotNull
        LocalDateTime dataHoraInicio,

        @NotNull
        LocalDateTime dataHoraFim,

        @NotNull
        Status status,

        String observacoes,

        @NotNull
        Double localizacaoCheckInLat,

        @NotNull
        Double localizacaoCheckInLong,

        @NotNull
        Double localizacaoCheckOutLat,

        @NotNull
        Double localizacaoCheckOutLong

) {
}
