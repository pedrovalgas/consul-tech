package com.pedrolucas.ConsulTech.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private String numero;
    private String cep;
}
