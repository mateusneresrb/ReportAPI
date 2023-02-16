package dev.mateusneres.report.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "endereco")

@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false, length = 75)
    private String bairro;

    @Column(nullable = false, length = 75)
    private String cidade;

    @Column(nullable = false, length = 75)
    private String estado;

    @Column(nullable = false, length = 8)
    private String pais;

    @Column(nullable = false, length = 45)
    private String cep;

    public Address(double latitude, double longitude, String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
    }


}

