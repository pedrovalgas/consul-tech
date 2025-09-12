package com.pedrolucas.ConsulTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consultor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_consultor", nullable = false)
    private String nome;

    @Column(name = "email_consultor", nullable = false, unique = true)
    private String email;

    @Column(name = "senha_consultor", nullable = false)
    private String senha;

    @Column(name = "telefone_consultor")
    private String telefone;

    private String cargo;

    private boolean ativo = true;

    private LocalDateTime dataCadastro = LocalDateTime.now();

}
