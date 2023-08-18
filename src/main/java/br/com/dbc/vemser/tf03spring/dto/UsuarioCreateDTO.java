package br.com.dbc.vemser.tf03spring.dto;

import lombok.Data;

@Data
public class UsuarioCreateDTO {
    private String login;
    private String senha;
}
