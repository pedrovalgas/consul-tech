package com.pedrolucas.ConsulTech.mapper;

import com.pedrolucas.ConsulTech.dto.consultor.ConsultorAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorCriarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorDetalhesResponse;
import com.pedrolucas.ConsulTech.model.Consultor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultorMapper {

    Consultor toEntity(ConsultorCriarRequest dto);

    ConsultorDetalhesResponse toDetalhes(Consultor entity);

    List<ConsultorDetalhesResponse> toDetalhesList(List<Consultor> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(@MappingTarget Consultor entity, ConsultorAtualizarRequest dto);

}
