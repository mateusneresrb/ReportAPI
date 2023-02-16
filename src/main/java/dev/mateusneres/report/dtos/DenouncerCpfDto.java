package dev.mateusneres.report.dtos;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class DenouncerCpfDto {

    @NotNull(message = "O cpf não pode ser nulo")
    private String cpf;

}
