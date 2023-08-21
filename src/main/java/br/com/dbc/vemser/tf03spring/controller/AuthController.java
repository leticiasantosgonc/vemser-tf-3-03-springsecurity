package br.com.dbc.vemser.tf03spring.controller;

import br.com.dbc.vemser.tf03spring.documentation.AuthControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.UsuarioDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.UsuarioEntity;
import br.com.dbc.vemser.tf03spring.security.TokenService;
import br.com.dbc.vemser.tf03spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/acesso")
@Validated
@RequiredArgsConstructor
public class AuthController implements AuthControllerDoc {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    @PostMapping
    public String auth(@RequestBody @Valid UsuarioCreateDTO usuarioDTO) throws RegraDeNegocioException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getLogin(),
                        usuarioDTO.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        UsuarioEntity usuarioValidado = (UsuarioEntity) authentication.getPrincipal();

        log.info("Auth: token gerado");

        return tokenService.generateToken(usuarioValidado);
    }

    @PostMapping("/cadastraradmin")
    public ResponseEntity<UsuarioDTO> createAdmin(@RequestBody UsuarioCreateDTO usuario){
        log.info("Auth: inserir novo");
        return new ResponseEntity<>(usuarioService.createAdmin(usuario), HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable Integer idUsuario){
        usuarioService.desativarUsuario(idUsuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alterarsenha/{idUsuario}")
    public ResponseEntity<UsuarioDTO> updatePassword(@PathVariable Integer idUsuario, @RequestBody String senha) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.alterarSenha(idUsuario, senha), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer idUsuario, @RequestBody UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.updateUsuario(idUsuario, usuarioCreateDTO), HttpStatus.OK);
    }

    @GetMapping("/retornarusuariologado")
    public ResponseEntity<UsuarioDTO> retornarUsuarioLogado() throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.retornarUsuarioLogado(), HttpStatus.OK);
    }

    @PostMapping("/cadastrarusuario")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO){
        return new ResponseEntity<>(usuarioService.createUsuario(usuarioCreateDTO), HttpStatus.OK);
    }

}