package dev.mateusneres.report.domain;

import dev.mateusneres.report.dtos.sub.DenouncerDto;
import dev.mateusneres.report.dtos.sub.ReportDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DataResponse {

    private long id;

    private double latitude;

    private double longitude;

    private DenouncerDto denunciante;

    private ReportDto denuncia;

    private Location endereco;

}
