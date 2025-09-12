package com.pedrolucas.ConsulTech.validacoes;

import com.pedrolucas.ConsulTech.exception.ValidacaoException;
import com.pedrolucas.ConsulTech.model.Visita;
import org.springframework.stereotype.Component;

@Component
public class ValidarDataInicioAnteriorADataFim implements ValidadorDeVisita{
    @Override
    public void validar(Visita visita) {
        if (visita.getDataHoraInicio().isAfter(visita.getDataHoraFim())){
            throw new ValidacaoException("Data de ínicio deve ser anterior a data de fim");
        }
    }
}
