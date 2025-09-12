package com.pedrolucas.ConsulTech.repository;

import com.pedrolucas.ConsulTech.model.Consultor;
import com.pedrolucas.ConsulTech.model.Status;
import com.pedrolucas.ConsulTech.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
    List<Visita> findByStatus(Status status);

    boolean existsByConsultorAndDataHoraInicioBeforeAndDataHoraFimAfter(Consultor consultor, LocalDateTime dataFim, LocalDateTime dataInicio);
}
