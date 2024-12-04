package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        return funcionario;
    }

    public Funcionario buscarPorId(Integer id) {
        return funcionarios.stream()
                           .filter(func -> func.getId().equals(id))
                           .findFirst()
                           .orElse(null);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }
}
