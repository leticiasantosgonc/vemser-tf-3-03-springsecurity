package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlunoCreateDTO {

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank
    @Schema(description = "Nome do aluno", required = true, example = "Rafael Silva")
    @Size(min = 2, max = 255)
    private String nome;

    @NotNull(message = "O campo idade não pode ser nulo")
    @Positive
    @Size(max = 125)
    @Schema(description = "Idade do aluno", required = true, example = "16")
    private String idade;

    @NotNull(message = "O campo CPF não pode ser nulo")
    @Schema(description = "CPF do aluno", required = true, example = "02815693518")
    @CPF
    @Size(max = 11)
    private String cpf;

    @Schema(description = "Número de matrícula do aluno", required = true, example = "8220188")
    @Size(max = 10)
    @Positive
    private String numeroDeMatricula;

    @Schema(description = "Insira o email do aluno", required = true, example = "teste@gmail.com")
    @Email(message = "Informe um endereço de e-mail válido")
    private String email;

}
