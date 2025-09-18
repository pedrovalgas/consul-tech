package com.pedrolucas.ConsulTech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorCriarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorDetalhesResponse;
import com.pedrolucas.ConsulTech.exception.ConsultorNaoEncontradoException;
import com.pedrolucas.ConsulTech.service.ConsultorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private ConsultorService service;


    @Test
    void deveSalvarConsultorNoRepository() throws Exception{
        var request = new ConsultorCriarRequest("Pedro", "pedro@email.com", "222222", "51098987678", "Consultor de vendas");
        var response = new ConsultorDetalhesResponse(1L,"Pedro", "pedro@email.com", "51098987678", "Consultor de vendas", LocalDateTime.now());

        when(service.criar(any(ConsultorCriarRequest.class))).thenReturn(response);

        mvc.perform(post("/consultor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(response.nome()))
                .andExpect(jsonPath("$.email").value(response.email()))
                .andExpect(jsonPath("$.telefone").value(response.telefone()))
                .andExpect(jsonPath("$.cargo").value(response.cargo()));
    }

    @Test
    void naoDeveSalvarConsultorComDadosInvalidos() throws Exception{
        var requestComErro = new ConsultorCriarRequest(null, null, "222222", "51894561234", "consultor");

        mvc.perform(post("/consultor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestComErro)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").exists())
                .andExpect(jsonPath("$.email").exists());

        verify(service, never()).criar(any(ConsultorCriarRequest.class));
    }



    @Test
    void deveListarTodosConsultores() throws Exception{
        //ARRANGE
        var consultor1 = new ConsultorDetalhesResponse(1l, "Artur", "artur@email.com", "51984627185", "Consultor", LocalDateTime.now());
        var consultor2 = new ConsultorDetalhesResponse(2l, "Marlom", "marlom@email.com", "51984627186", "Consultor de obras", LocalDateTime.now());
        List<ConsultorDetalhesResponse> consultores = Arrays.asList(consultor1, consultor2);

        when(service.listarTodos()).thenReturn(consultores);

        //ACT + ASSERT
        mvc.perform(get("/consultor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Artur"))
                .andExpect(jsonPath("$[0].email").value("artur@email.com"))
                .andExpect(jsonPath("$[1].nome").value("Marlom"))
                .andExpect(jsonPath("$[1].email").value("marlom@email.com"));
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumConsultorExistir()throws Exception{
        when(service.listarTodos()).thenReturn(Collections.emptyList());

        mvc.perform(get("/consultor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0))
                .andExpect(content().json("[]"));

    }

    @Test
    void deveRetornarConsultorBuscandoPeloId() throws Exception{
        var consultor = new ConsultorDetalhesResponse(1l, "Artur", "artur@email.com", "51984627185", "Consultor", LocalDateTime.now());

        when(service.buscarPorId(consultor.id())).thenReturn(consultor);

        mvc.perform(get("/consultor/{id}", consultor.id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(consultor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Artur"))
                .andExpect(jsonPath("$.email").value("artur@email.com"));
    }

    @Test
    void deveRetornar404QuandoIdNaoEncontradoEmBuscarPorId() throws Exception{
        Long idInexistente = 999L;

        when(service.buscarPorId(idInexistente)).thenThrow(new ConsultorNaoEncontradoException("Consultor não encontrado"));

        mvc.perform(get("/consultor/{id}", idInexistente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Consultor não encontrado"));

    }

    @Test
    void deveAtualizarConsultor() throws Exception{
        Long idExistente = 1L;
        var request = new ConsultorAtualizarRequest("Pedro Atualizado", "novo-pedro@email.com", "222222","51999999999", "Gerente de Vendas");
        var response = new ConsultorDetalhesResponse(
                idExistente,
                request.nome(),
                request.email(),
                request.telefone(),
                request.cargo(),
                LocalDateTime.now()
        );

        when(service.atualizar(eq(idExistente), any(ConsultorAtualizarRequest.class))).thenReturn(response);

        mvc.perform(put("/consultor/{id}", idExistente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idExistente))
                .andExpect(jsonPath("$.nome").value("Pedro Atualizado"))
                .andExpect(jsonPath("$.email").value("novo-pedro@email.com"));

        verify(service).atualizar(eq(idExistente), any(ConsultorAtualizarRequest.class));
    }

    @Test
    void deveRetornar404QuandoIdNaoEncontradoEmAtualizacao() throws Exception {
        Long idNaoExistente = 999L;
        var request = new ConsultorAtualizarRequest("Inexistente", "inexistente@email.com", "222222","51999999999", "Gerente");

        doThrow(new ConsultorNaoEncontradoException("Consultor não encontrado")).when(service).atualizar(eq(idNaoExistente), any(ConsultorAtualizarRequest.class));

        mvc.perform(put("/consultor/{id}", idNaoExistente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveExcluirConsultor() throws Exception{
        Long idExistente = 1L;

        doNothing().when(service).deletar(idExistente);

        mvc.perform(delete("/consultor/{id}", idExistente)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service).deletar(idExistente);
    }

    @Test
    void deveRetornar404QuandoDeletarConsultorInexistente() throws Exception{
        Long idInexistente = 999L;

        doThrow(new ConsultorNaoEncontradoException("Consultor não encontrado")).when(service).deletar(idInexistente);

        mvc.perform(delete("/consultor/{id}", idInexistente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}