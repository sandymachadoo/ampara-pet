package com.ampara_pet.ampara_pet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "interesses")
public class Interesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    @NotNull(message = "Animal é obrigatório")
    private Animal animal;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_interesse", nullable = false)
    private LocalDateTime dataInteresse;

    public Interesse() {
        this.dataInteresse = LocalDateTime.now();
    }

    public Interesse(Animal animal, String nome, String email, String telefone) {
        this();
        this.animal = animal;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Interesse(Animal animal, String nome, String email, String telefone, String mensagem) {
        this(animal, nome, email, telefone);
        this.mensagem = mensagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(LocalDateTime dataInteresse) {
        this.dataInteresse = dataInteresse;
    }
}