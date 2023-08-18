package br.com.dbc.vemser.tf03spring.controller;

import br.com.dbc.vemser.tf03spring.documentation.AuthControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.LoginDTO;
import br.com.dbc.vemser.tf03spring.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.UsuarioDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.UsuarioEntity;
import br.com.dbc.vemser.tf03spring.security.TokenService;
import br.com.dbc.vemser.tf03spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/acesso")
@Validated
@RequiredArgsConstructor
public class AuthController implements AuthControllerDoc {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

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

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioCreateDTO usuario){
        return new ResponseEntity<>(usuarioService.createUsuario(usuario), HttpStatus.OK);
    }
}