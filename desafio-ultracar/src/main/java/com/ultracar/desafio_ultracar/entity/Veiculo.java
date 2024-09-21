package com.ultracar.desafio_ultracar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "veiculos")
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String marca;

  private String modelo;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

}
