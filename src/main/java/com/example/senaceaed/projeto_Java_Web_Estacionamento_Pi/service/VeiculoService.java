package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Veiculo;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class VeiculoService {
    
    @Autowired
    VeiculoRepository veiculoRepository;

   public Veiculo salvarVeiculo(Veiculo veiculo) {
       
        if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Já existe um veículo com a placa: " + veiculo.getPlaca());
        }
        
        return veiculoRepository.save(veiculo); 
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();  
    }

     public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));  
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
      
        Veiculo veiculoEncontrado = buscarPorId(id);

        veiculoEncontrado.setPlaca(veiculo.getPlaca());
        veiculoEncontrado.setModelo(veiculo.getModelo());
        veiculoEncontrado.setValor(veiculo.getValor());
        veiculoEncontrado.setTipoPagamento(veiculo.getTipoPagamento());
        veiculoEncontrado.setDataRegistro(veiculo.getDataRegistro());
        veiculoEncontrado.setDataSaida(veiculo.getDataSaida());
        veiculoEncontrado.setFuncionario(veiculo.getFuncionario());

        return veiculoRepository.save(veiculoEncontrado); 
    }

    public void excluirVeiculo(Long id) {
        Veiculo veiculoEncontrado = buscarPorId(id);
        veiculoRepository.delete(veiculoEncontrado);  
    }

}
