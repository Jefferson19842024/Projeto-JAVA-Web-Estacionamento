package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.repository;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
       boolean existsByPlaca(String placa);
    
    
}
