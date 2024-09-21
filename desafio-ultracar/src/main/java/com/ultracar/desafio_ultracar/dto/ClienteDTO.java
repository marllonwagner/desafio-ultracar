package com.ultracar.desafio_ultracar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

  private Long id;
  private String nome;
  private String cpf;
  public String cep;
  private String numero;

  public ClienteDTO() {
  }


}
