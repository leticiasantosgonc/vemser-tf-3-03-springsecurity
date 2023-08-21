package br.com.dbc.vemser.tf03spring.documentation;

import br.com.dbc.vemser.tf03spring.dto.CursoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.dto.RelatorioCursoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

public interface CursoControllerDoc {

    @Operation(summary = "Lista todos os cursos", description = "Lista todos os cursos do banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os alunos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll();


    @Operation(summary = "Procura curso", description = "Procura curso pelo id no banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Procura professor pelo id."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idCurso}")
    ResponseEntity<CursoDTO> findById(@PathVariable ("idCurso") Integer idCurso) throws BancoDeDadosException, RegraDeNegocioException;


    @Operation(summary = "Cria um curso", description = "Cria um curso e o persiste no banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Curso criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoCreateDTO curso) throws Exception;


    @Operation(summary = "Atualiza um curso", description = "Atualiza um curso e o persiste no banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idCurso}")
    ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoCreateDTO curso, @PathVariable("idCurso") Integer idCurso) throws Exception;


    @Operation(summary = "Deleta um curso", description = "Deleta um professor do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Curso deletado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idCurso}")
    ResponseEntity<Void> delete (@PathVariable("idCurso") Integer idCurso) throws Exception;

    @Operation(summary = "Cria um relatório de cursos", description = "Cria um relatório de cursos com professores vinculados."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria um relatório de cursos com professores vinculados."),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/relatorio")
    public ResponseEntity<List<RelatorioCursoDTO>> createRelatorioCursoDTO();
}
