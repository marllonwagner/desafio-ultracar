package com.ultracar.desafio_ultracar.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para endereços.
 * Utilizado para transferir dados de endereço entre a camada de apresentação e a camada de serviço.
 */
@Getter
@Setter
public class EnderecoDTO {

  private Long id; // ID do endereço
  private String cep; // CEP do endereço
  private String logradouro; // Logradouro do endereço (rua, avenida, etc.)
  private String bairro; // Bairro do endereço
  private String uf; // Unidade Federativa (sigla do estado)
  private String estado; // Nome do estado
  private String localidade; // Cidade ou localidade do endereço

  // Construtor padrão
  public EnderecoDTO() {
  }
}
