package br.com.dbc.vemser.tf03spring.documentation;

import br.com.dbc.vemser.tf03spring.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.UsuarioDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface AuthControllerDoc {
    @Operation(summary = "Gerar token", description = "Gerar token de acesso para as rotas." +
            " Para isso, as informações do usuário a ser persistido deverão ser informadas no campo da" +
            " requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Gerar token de acesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public String auth(@RequestBody @Valid UsuarioCreateDTO usuarioDTO) throws RegraDeNegocioException;

    @Operation(summary = "Cria administrador", description = "Cria administrador no banco de dados." +
            "O acesso administrador tem todas as permissões do sistema, ou seja, pode realizar inserção, alteração, criação e delete em todas as entidades.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Administrador inserido no banco de dados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastrar/administrador")
    public ResponseEntity<UsuarioDTO> createAdmin(@RequestBody UsuarioCreateDTO usuario);

    @Operation(summary = "Deleta usuário", description = "Deleta usuário do banco de dados." +
            " Para isso, as informações do usuário a ser persistido deverão ser informadas no campo da" +
            " requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta usuário do banco de dados."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable Integer idUsuario);

    @Operation(summary = "Altera senha do usuário", description = "Altera senha do usuário no banco de dados." +
            " Para isso, as informações do usuário a ser persistido deverão ser informadas no campo da" +
            " requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Altera senha do usuário no banco de dados."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/alterar-senha/{idUsuario}")
    public ResponseEntity<UsuarioDTO> updatePassword(@PathVariable Integer idUsuario, @RequestBody String senha) throws RegraDeNegocioException;

    @Operation(summary = "Atualiza usuário", description = "Altera dados do usuário no banco de dados." +
            " Para isso, as informações do usuário a ser persistido deverão ser informadas no campo da" +
            " requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Altera dados do usuário no banco de dados."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer idUsuario, @RequestBody UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Lista dados do usuário logado", description = "Lista dados do usuário logado." +
            " Para isso, as informações do usuário a ser persistido deverão ser informadas no campo da" +
            " requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista dados do usuário logado."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/dados/logado")
    public ResponseEntity<UsuarioDTO> retornarUsuarioLogado() throws RegraDeNegocioException;

    @Operation(summary = "Cria usuário", description = "Cria usuário no banco de dados." +
            " O acesso usuário não tem todas as permissões do sistema, ou seja, pode apenas realizar o método GET de todas as entidades.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria usuário no banco de dados."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastrar/usuario")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO);

}
