package com.iff.devweb.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{P}\\p{Zs}\\p{M}]{1,45}$", message = "Caracteres inválidos ou limite excedido!")
    private String nome;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @Past
    private LocalDate dataNasc;

    @Column(nullable = false)
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O número de telefone deve seguir o formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
    private String tel;

    @Column(nullable = false)
    @Pattern(regexp = "^[\\p{L}\\p{N}\\p{P}\\p{Zs}\\p{M}]{1,250}$", message = "Caracteres inválidos ou limite excedido!")
    private String endereco;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDateTime dataCadastro;

    public Usuario(){}

    public Usuario(Long id, String nome, String email, LocalDate dataNasc, String tel, String endereco, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
        this.tel = tel;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
