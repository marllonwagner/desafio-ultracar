package com.ultracar.desafio_ultracar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

  private Long id;
  private String cep;
  private String logradouro;
  private String bairro;
  private String uf;
  private String estado;
  private String localidade;

  public EnderecoDTO() {
  }
}
