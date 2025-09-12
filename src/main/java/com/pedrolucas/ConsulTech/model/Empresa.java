package com.pedrolucas.ConsulTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_empresa")
    private String nome;

    private Endereco endereco;
    
    private double latitude;

    private double longitude;

    @Column(name = "nome_responsavel")
    private String responsavel;

    @Column(name = "telefone_empresa")
    private String telefoneContato;

    private String observacoes;

    private LocalDateTime dataCadastro;

}
