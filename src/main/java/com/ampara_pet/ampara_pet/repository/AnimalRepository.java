package com.ampara_pet.ampara_pet.repository;

import com.ampara_pet.ampara_pet.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByStatus(String status);

    List<Animal> findByEspecie(String especie);

    List<Animal> findByPorte(String porte);

    List<Animal> findByIdadeLessThanEqual(Integer idade);

    List<Animal> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a FROM Animal a WHERE a.status = 'DISPONÍVEL' ORDER BY a.dataCadastro DESC")
    List<Animal> findAllDisponiveis();

    @Query("SELECT COUNT(a) FROM Animal a WHERE a.status = 'DISPONÍVEL'")
    Long countAnimaisDisponiveis();

    @Query("SELECT a FROM Animal a WHERE " +
            "(:especie IS NULL OR a.especie = :especie) AND " +
            "(:porte IS NULL OR a.porte = :porte) AND " +
            "(:sexo IS NULL OR a.sexo = :sexo) AND " +
            "a.status = 'DISPONÍVEL' " +
            "ORDER BY a.dataCadastro DESC")
    List<Animal> findAnimaisComFiltros(
            @Param("especie") String especie,
            @Param("porte") String porte,
            @Param("sexo") String sexo);
}