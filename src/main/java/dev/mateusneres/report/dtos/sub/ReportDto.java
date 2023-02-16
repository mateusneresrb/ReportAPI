package dev.mateusneres.report.dtos.sub;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReportDto {

    @NotBlank(message = "O titulo não pode ser nulo")
    private String titulo;

    @NotBlank(message = "A descrição não pode ser nula")
    private String descricao;

}
