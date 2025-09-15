package com.pedrolucas.ConsulTech.validacoes;

import com.pedrolucas.ConsulTech.exception.ValidacaoException;
import com.pedrolucas.ConsulTech.model.Visita;
import com.pedrolucas.ConsulTech.repository.VisitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidadorConflitoDeHorario implements ValidadorDeVisita{

    private final VisitaRepository repository;

    @Override
    public void validar(Visita visita) {
        var consultor = visita.getConsultor();
        var dataInicio = visita.getDataHoraInicio();
        var dataFim = visita.getDataHoraFim();

        var validacao = repository.existsByConsultorAndDataHoraInicioBeforeAndDataHoraFimAfterAndIdNot(
                consultor, dataFim, dataInicio, visita.getId()
        );
        if (validacao){
            throw new ValidacaoException("Ja existem uma visita marcada nesse horário");
        }
    }
}
