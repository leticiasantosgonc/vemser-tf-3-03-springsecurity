package br.com.dbc.vemser.tf03spring.controller;


import br.com.dbc.vemser.tf03spring.dto.LoginDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.UsuarioEntity;
import br.com.dbc.vemser.tf03spring.security.TokenService;
import br.com.dbc.vemser.tf03spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );
        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);
        UsuarioEntity usuarioValidado = (UsuarioEntity) authentication.getPrincipal();
        return tokenService.generateToken(usuarioValidado);

    }

}
//        Optional<UsuarioEntity> byLoginAndSenha = usuarioService.findByLoginAndSenha(loginDTO.getLogin(), loginDTO.getSenha());
//        if (byLoginAndSenha.isPresent()) {
//            return tokenService.getToken(byLoginAndSenha.get());
//        } else {
//            throw new RegraDeNegocioException("usuário e senha inválidos");
//        }
