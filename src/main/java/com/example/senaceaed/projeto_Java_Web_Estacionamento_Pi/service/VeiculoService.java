package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Veiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private List<Veiculo> veiculos = new ArrayList<>(); 

    public void salvarVeiculo(Veiculo veiculo) {
        Optional<Veiculo> veiculoExistente = veiculos.stream()
        .filter(v -> v.getPlaca().equals(veiculo.getPlaca()))
        .findFirst();

        if (veiculoExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um veículo com a placa: " + veiculo.getPlaca());
        }

        int novoId = veiculos.size() + 1;
        veiculo.setId(novoId);
        veiculos.add(veiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return veiculos;
    }

    public Veiculo buscarPorId(Integer id) {
        Optional<Veiculo> veiculo = veiculos.stream()
        .filter(v -> v.getId().equals(id))
        .findFirst();
        return veiculo.orElse(null);
    }

    public void atualizarVeiculo(Veiculo veiculo) {
      
        veiculos.removeIf(v -> v.getId().equals(veiculo.getId()));
        veiculos.add(veiculo);  
    }

    public void excluirVeiculo(Integer id) {
        veiculos.removeIf(v -> v.getId().equals(id));
    }
}
