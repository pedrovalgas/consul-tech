package com.pedrolucas.ConsulTech.controller;

import com.pedrolucas.ConsulTech.dto.visita.VisitaAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.visita.VisitaCriarRequest;
import com.pedrolucas.ConsulTech.dto.visita.VisitaDetalhesResponse;
import com.pedrolucas.ConsulTech.model.Status;
import com.pedrolucas.ConsulTech.service.VisitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitas")
@RequiredArgsConstructor
public class VisitaController {

    private final VisitaService service;

    @PostMapping
    public ResponseEntity<VisitaDetalhesResponse> criarVisita(@RequestBody VisitaCriarRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VisitaDetalhesResponse>> listarTodasVisitas(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitaDetalhesResponse> buscarVisitaPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VisitaDetalhesResponse>> listarVisitasPorStatus(@PathVariable Status status){
        return ResponseEntity.ok(service.listarPorStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitaDetalhesResponse> atualizarVisita(@PathVariable Long id, @RequestBody VisitaAtualizarRequest dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVisita(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
