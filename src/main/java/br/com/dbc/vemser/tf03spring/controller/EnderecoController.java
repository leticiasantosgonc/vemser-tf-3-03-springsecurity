package br.com.dbc.vemser.tf03spring.controller;


import br.com.dbc.vemser.tf03spring.documentation.EnderecoControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.dto.RelatorioDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.EnderecoEntity;
import br.com.dbc.vemser.tf03spring.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@Validated
@ControllerAdvice
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoControllerDoc {

    private final EnderecoService enderecoService;
    @Autowired
    private ObjectMapper objectMapper;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }


    @GetMapping("/relatorio")
    public ResponseEntity<List<RelatorioDTO>> createRelatorioDTO() {
        log.info("Endereco: listar relatório de cursos");
        return new ResponseEntity<>(enderecoService.createRelatorioDTO(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException, RegraDeNegocioException {
        EnderecoEntity enderecoCriado = retornarEntidade(enderecoService.create(enderecoCreateDTO));

        if (ObjectUtils.isEmpty(enderecoCriado)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Endereco: inserir novo");

        return new ResponseEntity<>(retornarDTO(enderecoCriado), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() throws BancoDeDadosException {
        List<EnderecoDTO> todosOsEnderecos = enderecoService.findAll();

        if (ObjectUtils.isEmpty(todosOsEnderecos)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("Endereco: listar todos");

        return new ResponseEntity<>(todosOsEnderecos, HttpStatus.OK);
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable("idEndereco") @Positive Integer idEndereco) throws BancoDeDadosException {
        EnderecoDTO enderecoEncontrado = enderecoService.findById(idEndereco);

        if (ObjectUtils.isEmpty(enderecoEncontrado)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("Endereco: listar por id do endereco");

        return new ResponseEntity<>(enderecoEncontrado, HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") @Positive Integer idEndereco, @RequestBody @Valid EnderecoDTO enderecoDTO) throws RegraDeNegocioException {
        EnderecoDTO enderecoAtualizar = enderecoService.update(idEndereco, enderecoDTO);
        log.info("Endereco: editar pelo id");
        return new ResponseEntity<>(enderecoAtualizar, HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") @Positive Integer idEndereco) throws BancoDeDadosException {
        enderecoService.delete(idEndereco);
        log.info("Endereco: deletar pelo id");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public EnderecoEntity retornarEntidade(EnderecoCreateDTO enderecoCreateDTO) {
        return objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
    }

    public EnderecoDTO retornarDTO(EnderecoEntity enderecoEntity) {
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }
}


