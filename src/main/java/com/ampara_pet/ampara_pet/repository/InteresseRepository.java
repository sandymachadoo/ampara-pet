package com.ampara_pet.ampara_pet.repository;

import com.ampara_pet.ampara_pet.model.Interesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresseRepository extends JpaRepository<Interesse, Long> {

    List<Interesse> findByAnimalId(Long animalId);

    List<Interesse> findByEmail(String email);

    List<Interesse> findByTelefone(String telefone);

    @Query("SELECT i FROM Interesse i ORDER BY i.dataInteresse DESC")
    List<Interesse> findAllOrderByDataInteresse();

    @Query("SELECT COUNT(i) FROM Interesse i WHERE i.animal.id = :animalId")
    Long countByAnimalId(@Param("animalId") Long animalId);

    @Query("SELECT i FROM Interesse i WHERE i.animal.id = :animalId ORDER BY i.dataInteresse DESC")
    List<Interesse> findByAnimalIdOrderByData(@Param("animalId") Long animalId);
}