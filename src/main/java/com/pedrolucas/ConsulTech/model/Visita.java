package com.pedrolucas.ConsulTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "visitas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultor_id", nullable = false)
    private Consultor consultor;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private Long tempoTotalMinutos;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String observacoes;

    private Double localizacaoCheckInLat;

    private Double localizacaoCheckInLong;

    private Double localizacaoCheckOutLat;

    private Double localizacaoCheckOutLong;

}
