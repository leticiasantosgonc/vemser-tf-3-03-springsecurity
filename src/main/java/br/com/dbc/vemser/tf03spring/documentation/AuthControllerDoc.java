package br.com.dbc.vemser.tf03spring.documentation;

import br.com.dbc.vemser.tf03spring.dto.LoginDTO;
import br.com.dbc.vemser.tf03spring.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.UsuarioDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthControllerDoc {
    @Operation(summary = "Resgata Token", description = "Resgata token de acesso para as rotas." +
            " Para isso, as informações do usuário a ser persistido deverão ser informadas no campo da" +
            " requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Resgata token de acesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException;

    @Operation(summary = "Cria usuário", description = "Cria usuário para acesso as rotas." +
            " Para isso, as informações do usuário a ser persistido deverão ser informadas no campo da" +
            " requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário inserido no banco de dados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioCreateDTO usuario);
}
