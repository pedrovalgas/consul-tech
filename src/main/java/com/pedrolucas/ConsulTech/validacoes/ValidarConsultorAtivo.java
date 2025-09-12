package com.pedrolucas.ConsulTech.validacoes;

import com.pedrolucas.ConsulTech.exception.ValidacaoException;
import com.pedrolucas.ConsulTech.model.Visita;
import org.springframework.stereotype.Component;

@Component
public class ValidarConsultorAtivo implements ValidadorDeVisita{
    @Override
    public void validar(Visita visita) {
        if (!visita.getConsultor().isAtivo()){
            throw new ValidacaoException("Consultor não está ativo");
        }
    }
}
