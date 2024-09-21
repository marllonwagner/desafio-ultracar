package com.ultracar.desafio_ultracar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

  private Long id;
  public String nome;
  public String cpf;
  public String cep;


  public ClienteDTO() {
  }


}
