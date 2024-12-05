
package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "funcionario")
public class Funcionario {
  
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     @NotEmpty(message = "Nome não pode estar vazio")
    private String nome;

    @NotEmpty(message = "Cargo não pode estar vazio")
    private String cargo;
    private Integer codigoFuncionario;
     
    public Funcionario() {
    }

    public Funcionario(Long id, String nome, String cargo, Integer codigoFuncionario) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.codigoFuncionario = codigoFuncionario;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(Integer codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }
    
}
