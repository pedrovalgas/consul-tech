package com.pedrolucas.ConsulTech.mapper;

import com.pedrolucas.ConsulTech.dto.empresa.EmpresaAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.empresa.EmpresaCriarRequest;
import com.pedrolucas.ConsulTech.dto.empresa.EmpresaDetalhesResponse;
import com.pedrolucas.ConsulTech.model.Empresa;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    Empresa toEntity(EmpresaCriarRequest dto);

    EmpresaDetalhesResponse toDetalhes(Empresa entity);

    List<EmpresaDetalhesResponse> toDetalhesList(List<Empresa> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(@MappingTarget Empresa entity, EmpresaAtualizarRequest dto);

}
