package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProfessorCreateDTO {

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank
    @Schema(description = "Nome do professor", required = true, example = "Maria Rosa")
    private String nome;

    @NotNull(message = "O campo cpf não pode ser nulo")
    @CPF
    @Schema(description = "CPF do professor", required = true, example = "02815693518")
    private String cpf;

    @NotNull(message = "O campo especialidade não pode ser nulo")
    @NotBlank
    @Schema(description = "Especialidade do professor", required = true, example = "Matemática")
    private String especialidade;

    @NotNull(message = "O campo salario não pode ser nulo")
    @Positive
    @Schema(description = "Salário do professor", required = true, example = "5500")
    private Double salario;

}