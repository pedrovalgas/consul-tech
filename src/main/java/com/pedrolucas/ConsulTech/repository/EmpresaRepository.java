package com.pedrolucas.ConsulTech.repository;

import com.pedrolucas.ConsulTech.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
