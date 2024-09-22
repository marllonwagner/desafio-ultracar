package com.ultracar.desafio_ultracar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa um veículo no sistema.
 * Um veículo contém informações como placa, marca, modelo e ano.
 */
@Getter
@Setter
@Entity
@Table(name = "veiculos")
public class Veiculo {

  /**
   * Identificador único do veículo.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Placa do veículo, utilizada para identificação.
   */
  private String placa;

  /**
   * Marca do veículo, como Toyota, Ford, etc.
   */
  private String marca;

  /**
   * Modelo do veículo, como Corolla, Fiesta, etc.
   */
  private String modelo;

  /**
   * Ano de fabricação do veículo.
   */
  private Integer ano;

  /**
   * Cliente associado a este veículo.
   * Esta propriedade é ignorada durante a serialização JSON para evitar
   * referências circulares.
   */
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  @JsonIgnore
  private Cliente cliente;
}
