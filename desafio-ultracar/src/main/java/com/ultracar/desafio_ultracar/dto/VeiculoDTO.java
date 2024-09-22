package com.ultracar.desafio_ultracar.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO extends VeiculoInfoDTO {

  private Long cliente_id;
  private List<VeiculoDTO> veiculos;

  public VeiculoDTO() {}
}
