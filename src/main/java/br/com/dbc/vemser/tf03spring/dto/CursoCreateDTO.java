package br.com.dbc.vemser.tf03spring.dto;

import br.com.dbc.vemser.tf03spring.model.enums.TipoPeriodo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoCreateDTO {

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode estar vazio ou apenas com espaços em branco")
    @Schema(description = "Nome do curso", required = true, example = "Arquitetura de computadores")
    private String nome;

    @NotNull(message = "O campo cargaHoraria não pode ser nulo")
    @Positive
    @Schema(description = "Carga horária do curso", required = true, example = "60")
    private Integer cargaHoraria;

    @NotNull(message = "O campo periodo não pode ser nulo")
    @Schema(description = "Período do curso", required = true, example = "MANHA")
    private TipoPeriodo periodo;

    @NotNull
    @Positive
    private Integer idProfessor;

//    @Schema(description = "Descrição do curso", required = true)
//    @NotNull
//    @NotBlank
//    private String descricao;

}
