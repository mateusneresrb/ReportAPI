package dev.mateusneres.report.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "denunciante")

@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reportList"})
public class Denouncer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "denouncer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Report> reportList;

    public Denouncer(long id, String name, String cpf) {
        this.id = id;
        this.nome = name;
        this.cpf = cpf;
    }

    public Denouncer(String name, String cpf) {
        this.nome = name;
        this.cpf = cpf;
    }

}
