package com.pedrolucas.ConsulTech.service;

import com.pedrolucas.ConsulTech.dto.consultor.ConsultorAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorCriarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorDetalhesResponse;
import com.pedrolucas.ConsulTech.mapper.ConsultorMapper;
import com.pedrolucas.ConsulTech.repository.ConsultorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultorService {

    private final ConsultorRepository repository;
    private final ConsultorMapper mapper;

    public ConsultorDetalhesResponse criar(ConsultorCriarRequest dto){
        var consultor = mapper.toEntity(dto);
        return mapper.toDetalhes(repository.save(consultor));
    }

    public List<ConsultorDetalhesResponse> listarTodos(){
        var consultores = repository.findAllByAtivoTrue();
        return mapper.toDetalhesList(consultores);
    }

    public ConsultorDetalhesResponse buscarPorId(Long id){
        var consultor = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Consultor não encontrado"));
        return mapper.toDetalhes(consultor);
    }

    public ConsultorDetalhesResponse atualizar(Long id, ConsultorAtualizarRequest dto){
        var consultor = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Consultor não encontrado"));
        mapper.updateFromDto(consultor, dto);
        return mapper.toDetalhes(repository.save(consultor));
    }

    public void deletar(Long id) throws Throwable {
        var consultor = repository.findByIdAndAtivoTrue(id)
                        .orElseThrow(() -> new RuntimeException("Consultor não encontrado"));
        consultor.setAtivo(false);
        repository.save(consultor);
    }

}
