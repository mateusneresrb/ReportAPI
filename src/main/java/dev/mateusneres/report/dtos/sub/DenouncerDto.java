package dev.mateusneres.report.dtos.sub;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DenouncerDto {

    @NotBlank(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "O cpf não pode ser nulo") @Size(min = 11, max = 11)
    private String cpf;

}
