package com.ultracar.desafio_ultracar.repository;
import com.ultracar.desafio_ultracar.entity.Veiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
  // Métodos de consulta personalizados para o veículo podem ser adicionados aqui

}
