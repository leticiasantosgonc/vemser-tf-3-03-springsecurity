package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.UsuarioDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.CargoEntity;
import br.com.dbc.vemser.tf03spring.model.UsuarioEntity;
import br.com.dbc.vemser.tf03spring.repository.CargoRepository;
import br.com.dbc.vemser.tf03spring.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final CargoRepository cargoRepository;

    public Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }

    public Optional<UsuarioEntity> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<UsuarioEntity> findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public UsuarioDTO createUsuario(UsuarioCreateDTO usuario) {
        UsuarioEntity novoUsuario = new UsuarioEntity();
        CargoEntity cargo = cargoRepository.findById(2).get();
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        novoUsuario.setCargos(new HashSet<>());
        novoUsuario.getCargos().add(cargo);

        return retornarDTO(usuarioRepository.save(novoUsuario));
    }

    public UsuarioDTO createAdmin(UsuarioCreateDTO usuario) {
        UsuarioEntity novoUsuario = new UsuarioEntity();
        CargoEntity cargo = cargoRepository.findById(1).get();
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        novoUsuario.setCargos(new HashSet<>());
        novoUsuario.getCargos().add(cargo);

        return retornarDTO(usuarioRepository.save(novoUsuario));
    }

    public UsuarioDTO alterarSenha(Integer idUsuario, String senha) throws RegraDeNegocioException {
        UsuarioEntity usuarioAtualizar = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado.")) ;

        usuarioAtualizar.setSenha(passwordEncoder.encode(senha));

        return retornarDTO(usuarioRepository.save(usuarioAtualizar));
    }

    public void desativarUsuario(Integer idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }

    public UsuarioDTO updateUsuario(Integer idUsuario, UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        UsuarioEntity usuarioAtualizar = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RegraDeNegocioException("Usuario nao encontrado"));

        usuarioAtualizar.setLogin(usuarioCreateDTO.getLogin());
        usuarioAtualizar.setSenha(passwordEncoder.encode(usuarioCreateDTO.getSenha()));

        return retornarDTO(usuarioRepository.save(usuarioAtualizar));
    }

    public UsuarioDTO retornarUsuarioLogado() throws RegraDeNegocioException {
        return retornarDTO(findById(getIdLoggedUser()).orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!")));
    }

    public UsuarioDTO retornarDTO(UsuarioEntity usuarioEntity) {
        return objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
    }

    public Integer getIdLoggedUser() {
        String findUserId =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return Integer.parseInt(findUserId);
    }


}
