package com.ultracar.desafio_ultracar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

  private  String placa;

  private String marca;

  private String modelo;

  private Integer ano;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  @JsonIgnore
  private Cliente cliente;

}
