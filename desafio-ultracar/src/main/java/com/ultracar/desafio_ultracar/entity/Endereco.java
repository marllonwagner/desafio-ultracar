package com.ultracar.desafio_ultracar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa um endereço no sistema.
 * Um endereço contém informações sobre localização, incluindo CEP,
 * logradouro, bairro, estado, e localidade.
 */
@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class Endereco {

  /**
   * Identificador único do endereço.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Código de Endereçamento Postal (CEP) do endereço.
   */
  private String cep;

  /**
   * Logradouro do endereço, como rua ou avenida.
   */
  private String logradouro;

  /**
   * Bairro onde o endereço está localizado.
   */
  private String bairro;

  /**
   * Unidade Federativa (UF) do endereço, como SP, RJ, etc.
   */
  private String uf;

  /**
   * Nome do estado do endereço, como São Paulo ou Rio de Janeiro.
   */
  private String estado;

  /**
   * Localidade do endereço, geralmente a cidade.
   */
  private String localidade;

  /**
   * Cliente associado a este endereço.
   * Esta propriedade é ignorada durante a serialização JSON para evitar
   * referências circulares.
   */
  @JsonIgnore
  @OneToOne(mappedBy = "endereco")
  private Cliente cliente; // O mapeamento correto
}
