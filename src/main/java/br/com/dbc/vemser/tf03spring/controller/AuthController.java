package br.com.dbc.vemser.tf03spring.controller;

import br.com.dbc.vemser.tf03spring.dto.LoginDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.UsuarioEntity;
import br.com.dbc.vemser.tf03spring.security.TokenService;
import br.com.dbc.vemser.tf03spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        Optional<UsuarioEntity> byLoginAndSenha = usuarioService.findByLoginAndSenha(loginDTO.getLogin(), loginDTO.getSenha());
        if (byLoginAndSenha.isPresent()) {
            return tokenService.getToken(byLoginAndSenha.get());
        } else {
            throw new RegraDeNegocioException("usuário e senha inválidos");
        }
    }
}
