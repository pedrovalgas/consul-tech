package com.pedrolucas.ConsulTech.service;

import com.pedrolucas.ConsulTech.dto.visita.VisitaAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.visita.VisitaCriarRequest;
import com.pedrolucas.ConsulTech.dto.visita.VisitaDetalhesResponse;
import com.pedrolucas.ConsulTech.exception.ConsultorNaoEncontradoException;
import com.pedrolucas.ConsulTech.exception.EmpresaNaoEncontradaException;
import com.pedrolucas.ConsulTech.exception.VisitaNaoEncontradaException;
import com.pedrolucas.ConsulTech.mapper.VisitaMapper;
import com.pedrolucas.ConsulTech.model.Status;
import com.pedrolucas.ConsulTech.repository.ConsultorRepository;
import com.pedrolucas.ConsulTech.repository.EmpresaRepository;
import com.pedrolucas.ConsulTech.repository.VisitaRepository;
import com.pedrolucas.ConsulTech.validacoes.ValidadorDeVisita;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitaService {

    private final VisitaRepository repository;
    private final VisitaMapper mapper;
    private final ConsultorRepository consultorRepository;
    private final EmpresaRepository empresaRepository;
    private final List<ValidadorDeVisita> validadores;

    @Transactional
    public VisitaDetalhesResponse criar(VisitaCriarRequest dto){
        var consultor = consultorRepository.findById(dto.consultorId())
                .orElseThrow(() -> new ConsultorNaoEncontradoException("Consultor não existe"));
        var empresa = empresaRepository.findById(dto.empresaId())
                .orElseThrow(() -> new EmpresaNaoEncontradaException("Empresa não existe"));
        var visita = mapper.toEntity(dto);
        visita.setConsultor(consultor);
        visita.setEmpresa(empresa);

        validadores.forEach(validador -> validador.validar(visita));

        return mapper.toDetalhes(repository.save(visita));
    }

    public List<VisitaDetalhesResponse> listarTodas(){
        var visitas = repository.findAll();
        return mapper.toDetalhesList(visitas);
    }

    public List<VisitaDetalhesResponse> listarPorStatus(Status status){
        var visita = repository.findByStatus(status);
        return mapper.toDetalhesList(visita);
    }

    public VisitaDetalhesResponse buscarPorId(Long id){
        var visita = repository.findById(id)
                .orElseThrow(() -> new VisitaNaoEncontradaException("Visita não encontrada"));
        return mapper.toDetalhes(visita);
    }

    @Transactional
    public VisitaDetalhesResponse atualizar(Long id, VisitaAtualizarRequest dto){
        var visita = repository.findById(id)
                .orElseThrow(() -> new VisitaNaoEncontradaException("Visita não encontrada"));
        mapper.updateFromDto(visita, dto);

        validadores.forEach(validador -> validador.validar(visita));

        return mapper.toDetalhes(repository.save(visita));
    }

    @Transactional
    public void deletar(Long id){
        var visita = repository.findById(id)
                .orElseThrow(() -> new VisitaNaoEncontradaException("Visita não encontrada"));
        repository.delete(visita);
    }

}
