package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.model.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Integer>{

//    @Query("SELECT * FROM CARGO WHERE ID_CARGO = 2")
    Optional<CargoEntity> findById(Integer idCargo);

//    @Query("SELECT * FROM CARGO WHERE ID_CARGO = 1")
//    CargoEntity retornarCargoAdmin();
}
