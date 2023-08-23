package br.com.dbc.vemser.tf03spring.controller;


import br.com.dbc.vemser.tf03spring.documentation.CursoControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.CursoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.dto.RelatorioCursoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.service.CursoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/curso")
public class CursoController implements CursoControllerDoc {
    private final CursoService cursoService;
    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() {
        log.info("Curso: listar todos");
        return new ResponseEntity<>(cursoService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/relatorio")
    public ResponseEntity<List<RelatorioCursoDTO>> createRelatorioCursoDTO(){
        log.info("Curso: listar relatório de cursos");
        return new ResponseEntity<>(cursoService.createRelatorioCursoDTO(), HttpStatus.OK);
    }
    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> findById(@PathVariable("idCurso") Integer idCurso) throws BancoDeDadosException, RegraDeNegocioException {
        CursoDTO cursoEncontrado = cursoService.findById(idCurso);

        log.info("Curso: listar por id do curso");
        return new ResponseEntity<>(cursoEncontrado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoCreateDTO curso) throws Exception{
        log.info("Curso: inserir novo");
        return new ResponseEntity<>(cursoService.create(curso), HttpStatus.OK);
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoCreateDTO curso, @PathVariable ("idCurso") Integer idCurso) throws Exception{
        log.info("Curso: editar pelo id");
        return new ResponseEntity<>(cursoService.update(curso, idCurso), HttpStatus.OK);
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> delete(@PathVariable("idCurso") Integer idCurso) throws Exception{
        cursoService.delete(idCurso);
        log.info("Curso: deletar pelo id");
        return ResponseEntity.ok().build();
    }

}
