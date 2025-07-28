package com.ampara_pet.ampara_pet.controller;

import com.ampara_pet.ampara_pet.model.Animal;
import com.ampara_pet.ampara_pet.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/animais")
@CrossOrigin(origins = "*")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping
    public ResponseEntity<Animal> cadastrarAnimal(@RequestBody Animal animal) {
        try {

            if (animal.getDataCadastro() == null) {
                animal.setDataCadastro(LocalDateTime.now());
            }

            if (animal.getStatus() == null) {
                animal.setStatus("DISPONÍVEL");
            }

            Animal novoAnimal = animalRepository.save(animal);
            return ResponseEntity.ok(novoAnimal);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listarAnimaisDisponiveis() {
        List<Animal> animais = animalRepository.findAllDisponiveis();
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarAnimalPorId(@PathVariable Long id) {
        return animalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Animal>> buscarPorEspecie(@PathVariable String especie) {
        List<Animal> animais = animalRepository.findByEspecie(especie);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/porte/{porte}")
    public ResponseEntity<List<Animal>> buscarPorPorte(@PathVariable String porte) {
        List<Animal> animais = animalRepository.findByPorte(porte);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Animal>> buscarPorNome(@RequestParam String nome) {
        List<Animal> animais = animalRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<Animal>> buscarComFiltros(
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) String porte,
            @RequestParam(required = false) String sexo) {

        List<Animal> animais = animalRepository.findAnimaisComFiltros(especie, porte, sexo);
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/contar")
    public ResponseEntity<Long> contarAnimaisDisponiveis() {
        Long quantidade = animalRepository.countAnimaisDisponiveis();
        return ResponseEntity.ok(quantidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        return animalRepository.findById(id).map(animalExistente -> {
            animalExistente.setNome(animalAtualizado.getNome());
            animalExistente.setEspecie(animalAtualizado.getEspecie());
            animalExistente.setPorte(animalAtualizado.getPorte());
            animalExistente.setSexo(animalAtualizado.getSexo());
            animalExistente.setIdade(animalAtualizado.getIdade());
            animalExistente.setDescricao(animalAtualizado.getDescricao());
            animalExistente.setStatus(animalAtualizado.getStatus());
            animalExistente.setFotoUrl(animalAtualizado.getFotoUrl());
            animalExistente.setDataAtualizacao(LocalDateTime.now());
            Animal atualizado = animalRepository.save(animalExistente);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}