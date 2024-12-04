
package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model;

public class Funcionario {
  
    private Integer id;
    private String nome;
    private String cargo;

    public Funcionario() {
    }

    
    
    public Funcionario(Integer id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
