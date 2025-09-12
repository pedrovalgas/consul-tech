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

    @Column(name = "nome_empresa", nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;

    private Double latitude;

    private Double longitude;

    @Column(name = "nome_responsavel")
    private String responsavel;

    @Column(name = "telefone_empresa")
    private String telefoneContato;

    private String observacoes;

    private LocalDateTime dataCadastro = LocalDateTime.now();

}
