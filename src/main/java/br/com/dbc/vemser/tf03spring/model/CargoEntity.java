package br.com.dbc.vemser.tf03spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "CARGO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CargoEntity {

    @Column (name = "nome")
    private String nome;

    @Column (name = "id_cargo")
    @Id
    private Integer idCargo;

    @JsonIgnore
    @ManyToMany(mappedBy = "cargos")
    private Set<UsuarioEntity> usuarios;

    public String getAuthority(){return nome;}
}
