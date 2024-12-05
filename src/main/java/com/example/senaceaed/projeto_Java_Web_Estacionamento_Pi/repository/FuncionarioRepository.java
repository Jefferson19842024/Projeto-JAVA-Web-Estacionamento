
package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.repository;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
     boolean existsByCodigoFuncionario(Integer codigoFuncionario);
}
