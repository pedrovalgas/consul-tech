package com.pedrolucas.ConsulTech.controller;

import com.pedrolucas.ConsulTech.dto.consultor.ConsultorAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorCriarRequest;
import com.pedrolucas.ConsulTech.dto.consultor.ConsultorDetalhesResponse;
import com.pedrolucas.ConsulTech.service.ConsultorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultor")
@RequiredArgsConstructor
public class ConsultorController {

    private final ConsultorService service;

    @PostMapping
    public ResponseEntity<ConsultorDetalhesResponse> criarConsultor(@RequestBody @Valid ConsultorCriarRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConsultorDetalhesResponse>> listarTodosConsultores(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultorDetalhesResponse> buscarConsultorPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultorDetalhesResponse> atualizarConsultor(@PathVariable Long id, @RequestBody ConsultorAtualizarRequest dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsultor(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
