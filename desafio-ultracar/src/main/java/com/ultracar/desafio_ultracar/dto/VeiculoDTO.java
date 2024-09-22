package com.ultracar.desafio_ultracar.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para veículos.
 * Extende a classe VeiculoInfoDTO e adiciona informações específicas para o cliente.
 */
@Getter
@Setter
public class VeiculoDTO extends VeiculoInfoDTO {

  private Long cliente_id; // ID do cliente ao qual o veículo pertence
  private List<VeiculoDTO> veiculos; // Lista de veículos relacionados

  // Construtor padrão
  public VeiculoDTO() {}
}
