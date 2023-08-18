package br.com.dbc.vemser.tf03spring.dto;

import lombok.Data;

@Data
public class RelatorioCursoDTO {

    private Integer cargaHoraria;
    private String nome;
    private String cpf;
    private String especialidade;

    public RelatorioCursoDTO( Integer cargaHoraria, String nome, String cpf, String especialidade) {

        this.cargaHoraria = cargaHoraria;
        this.nome = nome;
        this.cpf = cpf;
        this.especialidade = especialidade;
    }
}
