package com.ultracar.desafio_ultracar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoInfoDTO {
  private String marca;
  private String modelo;
  private String placa;
  private Integer ano;

  public VeiculoInfoDTO() {}
}
