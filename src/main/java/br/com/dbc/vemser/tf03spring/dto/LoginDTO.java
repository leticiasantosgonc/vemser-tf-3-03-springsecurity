package br.com.dbc.vemser.tf03spring.dto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
    @NotNull
    private String login;
    @NotNull
    private String senha;
}
