package dev.mateusneres.report.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
