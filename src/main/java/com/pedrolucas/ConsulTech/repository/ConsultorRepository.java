package com.pedrolucas.ConsulTech.repository;

import com.pedrolucas.ConsulTech.dto.consultor.ConsultorDetalhesResponse;
import com.pedrolucas.ConsulTech.model.Consultor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultorRepository extends JpaRepository<Consultor, Long> {
    List<Consultor> findAllByAtivoTrue();

    Optional<Consultor> findByIdAndAtivoTrue(Long id);
}
