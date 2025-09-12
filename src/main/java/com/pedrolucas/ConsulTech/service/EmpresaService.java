package com.pedrolucas.ConsulTech.service;

import com.pedrolucas.ConsulTech.dto.empresa.EmpresaAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.empresa.EmpresaCriarRequest;
import com.pedrolucas.ConsulTech.dto.empresa.EmpresaDetalhesResponse;
import com.pedrolucas.ConsulTech.exception.EmpresaNaoEncontradaException;
import com.pedrolucas.ConsulTech.mapper.EmpresaMapper;
import com.pedrolucas.ConsulTech.model.Empresa;
import com.pedrolucas.ConsulTech.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository repository;
    private final EmpresaMapper mapper;


    @Transactional
    public EmpresaDetalhesResponse criar(EmpresaCriarRequest dto){
        var empresa = mapper.toEntity(dto);
        return mapper.toDetalhes(repository.save(empresa));
    }

    public List<EmpresaDetalhesResponse> listarTodas(){
        var empresas = repository.findAll();
        return mapper.toDetalhesList(empresas);
    }

    public EmpresaDetalhesResponse buscarPorId(Long id){
        var empresa = repository.findById(id)
                .orElseThrow(() -> new EmpresaNaoEncontradaException("Empresa não encontrada"));
        return mapper.toDetalhes(empresa);
    }

    @Transactional
    public EmpresaDetalhesResponse atualizar(Long id, EmpresaAtualizarRequest dto){
        var empresa = repository.findById(id)
                .orElseThrow(() -> new EmpresaNaoEncontradaException("Empresa não encontrada"));
        mapper.updateFromDto(empresa, dto);
        return mapper.toDetalhes(repository.save(empresa));
    }

    @Transactional
    public void deletar(Long id){
        var empresa = repository.findById(id)
                .orElseThrow(() -> new EmpresaNaoEncontradaException("Empresa não encontrada"));
        repository.delete(empresa);
    }

}
