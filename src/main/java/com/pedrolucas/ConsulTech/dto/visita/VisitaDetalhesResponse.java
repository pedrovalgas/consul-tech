package com.pedrolucas.ConsulTech.dto.visita;

import com.pedrolucas.ConsulTech.model.Consultor;
import com.pedrolucas.ConsulTech.model.Empresa;
import com.pedrolucas.ConsulTech.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record VisitaDetalhesResponse(
       Long id,

       Consultor consultor,

       Empresa empresa,

       LocalDateTime dataHoraInicio,

       LocalDateTime dataHoraFim,

       Long tempoTotalMinutos,

       Status status,

       String observacoes,

       Double localizacaoCheckInLat,

       Double localizacaoCheckInLong,

       Double localizacaoCheckOutLat,

       Double localizacaoCheckOutLong
) {
}
