package com.ultracar.desafio_ultracar.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa um cliente no sistema.
 * Um cliente possui um nome, CPF, um endereço e uma lista de veículos associados.
 */
@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

  /**
   * Identificador único do cliente.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nome do cliente.
   */
  private String nome;

  /**
   * CPF do cliente. Deve ser único.
   */
  private String cpf;

  /**
   * Endereço associado ao cliente.
   * O endereço é gerenciado por meio de operações de cascata.
   */
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "endereco_id")
  private Endereco endereco;

  /**
   * Lista de veículos associados ao cliente.
   * A relação é mapeada pelo atributo 'cliente' na entidade Veiculo.
   * As operações de cascata são aplicadas.
   */
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Veiculo> veiculos;
}
