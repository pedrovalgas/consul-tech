package com.pedrolucas.ConsulTech.mapper;

import com.pedrolucas.ConsulTech.dto.visita.VisitaAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.visita.VisitaCriarRequest;
import com.pedrolucas.ConsulTech.dto.visita.VisitaDetalhesResponse;
import com.pedrolucas.ConsulTech.model.Visita;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitaMapper {

    Visita toEntity(VisitaCriarRequest dto);

    VisitaDetalhesResponse toDetalhes(Visita entity);

    List<VisitaDetalhesResponse> toDetalhesList(List<Visita> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(@MappingTarget Visita entity, VisitaAtualizarRequest dto);

}
