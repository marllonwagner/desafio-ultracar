package com.ultracar.desafio_ultracar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cep;
  private String logradouro;
  private String bairro;
  private String uf;
  private String estado;
  private String localidade;

  @JsonIgnore
  @OneToOne(mappedBy = "endereco")
  private Cliente cliente; // O mapeamento correto
}
