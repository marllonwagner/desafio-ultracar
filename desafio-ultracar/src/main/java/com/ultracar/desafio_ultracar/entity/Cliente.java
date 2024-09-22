package com.ultracar.desafio_ultracar.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String cpf;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "endereco_id")
  private Endereco endereco;

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Veiculo> veiculos;
}
