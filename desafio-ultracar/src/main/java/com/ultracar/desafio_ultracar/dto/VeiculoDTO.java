package com.ultracar.desafio_ultracar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO {

  private Long cliente_id;
  private String marca;
  private String modelo;
  private String placa;
  private Integer ano;

  public VeiculoDTO() {}
}
