package com.ultracar.desafio_ultracar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática pelo banco
  private Long id;
  private String nome;
  private String cpf;

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Veiculo> veiculos;

  @Embedded
  private Endereco endereco;
}
