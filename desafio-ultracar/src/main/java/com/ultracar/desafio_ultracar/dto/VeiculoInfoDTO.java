package com.ultracar.desafio_ultracar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para informações detalhadas de um veículo.
 */
@Getter
@Setter
public class VeiculoInfoDTO {

  @NotBlank(message = "Marca não pode ser vazia")
  private String marca; // Marca do veículo

  @NotBlank(message = "Modelo não pode ser vazio")
  private String modelo; // Modelo do veículo

  @NotBlank(message = "Placa não pode ser vazia")
  @Pattern(regexp = "^[A-Z]{3}\\d{4}$", message = "Placa deve estar no formato ABC1234")
  private String placa; // Placa do veículo no formato especificado

  @NotNull(message = "Ano não pode ser nulo")
  private Integer ano; // Ano de fabricação do veículo

  // Construtor padrão
  public VeiculoInfoDTO() {}
}
