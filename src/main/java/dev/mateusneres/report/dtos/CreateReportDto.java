package dev.mateusneres.report.dtos;

import dev.mateusneres.report.dtos.sub.DenouncerDto;
import dev.mateusneres.report.dtos.sub.ReportDto;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class CreateReportDto {

    @NotNull(message = "A latitude não pode ser nula")
    private double latitude;

    @NotNull(message = "A longitude não pode ser nula")
    private double longitude;

    @Valid
    @NotNull(message = "O denunciante não pode ser nulo")
    private DenouncerDto denunciante;

    @Valid
    @NotNull(message = "A denuncia não pode ser nula")
    private ReportDto denuncia;

}
