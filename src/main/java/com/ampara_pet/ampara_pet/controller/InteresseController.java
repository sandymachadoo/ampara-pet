package com.ampara_pet.ampara_pet.controller;

import com.ampara_pet.ampara_pet.model.Interesse;
import com.ampara_pet.ampara_pet.repository.InteresseRepository;
import com.ampara_pet.ampara_pet.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interesses")
@CrossOrigin(origins = "*")
public class InteresseController {

    @Autowired
    private InteresseRepository interesseRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping
    public ResponseEntity<Interesse> demonstrarInteresse(@RequestBody Interesse interesse) {

        if (!animalRepository.existsById(interesse.getAnimal().getId())) {
            return ResponseEntity.badRequest().build();
        }

        Interesse novoInteresse = interesseRepository.save(interesse);
        return ResponseEntity.ok(novoInteresse);
    }

    @GetMapping
    public ResponseEntity<List<Interesse>> listarTodosInteresses() {
        List<Interesse> interesses = interesseRepository.findAllOrderByDataInteresse();
        return ResponseEntity.ok(interesses);
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<Interesse>> buscarInteressesPorAnimal(@PathVariable Long animalId) {
        List<Interesse> interesses = interesseRepository.findByAnimalIdOrderByData(animalId);
        return ResponseEntity.ok(interesses);
    }

    @GetMapping("/email")
    public ResponseEntity<List<Interesse>> buscarPorEmail(@RequestParam String email) {
        List<Interesse> interesses = interesseRepository.findByEmail(email);
        return ResponseEntity.ok(interesses);
    }

    @GetMapping("/telefone")
    public ResponseEntity<List<Interesse>> buscarPorTelefone(@RequestParam String telefone) {
        List<Interesse> interesses = interesseRepository.findByTelefone(telefone);
        return ResponseEntity.ok(interesses);
    }

    @GetMapping("/animal/{animalId}/contar")
    public ResponseEntity<Long> contarInteressesPorAnimal(@PathVariable Long animalId) {
        Long quantidade = interesseRepository.countByAnimalId(animalId);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interesse> buscarInteressePorId(@PathVariable Long id) {
        return interesseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}