package br.com.dbc.vemser.tf03spring.service;


import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.ProfessorDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.AlunoEntity;
import br.com.dbc.vemser.tf03spring.model.ProfessorEntity;
import br.com.dbc.vemser.tf03spring.repository.ProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;

    public ProfessorDTO create(ProfessorCreateDTO professorDTO){
            ProfessorEntity professorCriado = retornarEntity(professorDTO);
            ProfessorEntity professorEnviar = professorRepository.save(professorCriado);
            return retornarDTO(professorEnviar);
    }

    public List<ProfessorDTO> findAll() {
        List<ProfessorEntity> listProfessores = professorRepository.findAll();
        List<ProfessorDTO> professoresDTO = new ArrayList<>();

        for (ProfessorEntity professorEntity : listProfessores) {
            professoresDTO.add(retornarDTO(professorEntity));
        }
        return professoresDTO;
    }

    public Page<ProfessorDTO> findAll(Pageable pageable) {
        Page<ProfessorEntity> professorEntityPage = professorRepository.findAll(pageable);
        List<ProfessorDTO> professoresEncontrados = professorRepository
                .findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();

        return new PageImpl<>(professoresEncontrados, pageable, professorEntityPage.getTotalElements());
    }

    public ProfessorDTO findById(Integer idProfessor) throws RegraDeNegocioException {
        ProfessorEntity professorEncontrado = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RegraDeNegocioException("Professor não encontrado"));
        return retornarDTO(professorEncontrado);
    }

    public ProfessorDTO update(Integer idProfessor, ProfessorCreateDTO professorDTO) throws RegraDeNegocioException {
        ProfessorEntity professorAtualizar = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RegraDeNegocioException("Professor não encontrado"));
        professorAtualizar.setCpf(professorDTO.getCpf());
        professorAtualizar.setNome(professorDTO.getNome());
        professorAtualizar.setSalario(professorDTO.getSalario());
        professorAtualizar.setEspecialidade(professorDTO.getEspecialidade());

        ProfessorEntity professorAtualizado = professorRepository.save(professorAtualizar);
        return retornarDTO(professorAtualizado);

    }

    public void delete(Integer idProfessor) throws RegraDeNegocioException{
        professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RegraDeNegocioException("Professor não encontrado"));
        professorRepository.deleteById(idProfessor);
    }
    public ProfessorEntity retornarEntity(ProfessorCreateDTO professorDTO){
        return objectMapper.convertValue(professorDTO, ProfessorEntity.class);
    }

    public ProfessorDTO retornarDTO(ProfessorEntity professorEntity){
        return objectMapper.convertValue(professorEntity, ProfessorDTO.class);
    }

    private ProfessorDTO convertToDTO(ProfessorEntity professorEntity) {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setIdProfessor(professorEntity.getIdProfessor());
        professorDTO.setNome(professorEntity.getNome());
        professorDTO.setCpf(professorEntity.getCpf());
        professorDTO.setSalario(professorEntity.getSalario());
        professorDTO.setEspecialidade(professorEntity.getEspecialidade());
        return professorDTO;
    }

}
