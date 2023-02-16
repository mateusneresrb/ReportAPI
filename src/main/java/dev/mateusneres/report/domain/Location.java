package dev.mateusneres.report.domain;

import lombok.Data;

@Data
public class Location {

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    private String cep;
}
