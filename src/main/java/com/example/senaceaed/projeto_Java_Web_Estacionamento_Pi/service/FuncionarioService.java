package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Funcionario;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
 
    public Funcionario buscarPorId(Long id) { 
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
    }

    public Funcionario criarFuncionario(Funcionario funcionario) {
          if (funcionarioRepository.existsByCodigoFuncionario(funcionario.getCodigoFuncionario())) {
        throw new RuntimeException("Código do funcionário já existe.");
    }
        funcionario.setId(null); 
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario atualizar(Long id, Funcionario funcionario) {  
        Funcionario funcionarioEncontrado = buscarPorId(id);
        funcionarioEncontrado.setNome(funcionario.getNome());
        funcionarioEncontrado.setCargo(funcionario.getCargo());
        funcionarioEncontrado.setCodigoFuncionario(funcionario.getCodigoFuncionario());
        funcionarioRepository.save(funcionarioEncontrado);
        return funcionarioEncontrado;
    }

    public void excluir(Long id) { 
        Funcionario funcionarioEncontrado = buscarPorId(id);
        funcionarioRepository.deleteById(funcionarioEncontrado.getId());
    }
}
