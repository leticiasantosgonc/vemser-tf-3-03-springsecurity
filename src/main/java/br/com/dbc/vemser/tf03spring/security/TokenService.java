package br.com.dbc.vemser.tf03spring.security;


import br.com.dbc.vemser.tf03spring.model.UsuarioEntity;
import br.com.dbc.vemser.tf03spring.service.UsuarioService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    static final String HEADER_STRING = "Authorization";
    private final UsuarioService usuarioService;

    public String getToken(UsuarioEntity usuarioEntity) {
        String tokenTexto = usuarioEntity.getLogin() + ";" + usuarioEntity.getSenha();
        String token = Base64.getEncoder().encodeToString(tokenTexto.getBytes());
        return token;
    }

    public Optional<UsuarioEntity> isValid(String token) {
        if(token == null){
            return Optional.empty();
        }
        byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
        String decoded = new String(decodedBytes);
        String[] split = decoded.split(";");
        return usuarioService.findByLoginAndSenha(split[0], split[1]);
    }
}