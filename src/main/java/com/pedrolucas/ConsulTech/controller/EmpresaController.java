package com.pedrolucas.ConsulTech.controller;

import com.pedrolucas.ConsulTech.dto.empresa.EmpresaAtualizarRequest;
import com.pedrolucas.ConsulTech.dto.empresa.EmpresaCriarRequest;
import com.pedrolucas.ConsulTech.dto.empresa.EmpresaDetalhesResponse;
import com.pedrolucas.ConsulTech.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService service;

    @PostMapping
    public ResponseEntity<EmpresaDetalhesResponse> criarEmpresa(@RequestBody @Valid EmpresaCriarRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDetalhesResponse>> listarTodasEmpresas(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDetalhesResponse> buscarEmpresaPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDetalhesResponse> atualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaAtualizarRequest dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}