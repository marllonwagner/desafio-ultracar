package com.ultracar.desafio_ultracar.repository;
import com.ultracar.desafio_ultracar.entity.Endereco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
  // Aqui você pode adicionar consultas personalizadas, se necessário

}
