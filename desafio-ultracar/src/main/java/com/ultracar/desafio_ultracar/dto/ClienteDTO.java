package com.ultracar.desafio_ultracar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) para clientes.
 * Utilizado para transferir dados entre a camada de apresentação e a camada de serviço.
 */
@Getter
@Setter
public class ClienteDTO {

  private Long id; // ID do cliente

  @NotBlank(message = "Nome não pode ser vazio")
  private String nome; // Nome do cliente

  @NotBlank(message = "CPF não pode ser vazio")
  @Pattern(regexp = "^(?!(\\d)\\1{10})\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
      message = "CPF deve estar no formato XXX.XXX.XXX-XX")
  private String cpf; // CPF do cliente, deve seguir o formato XXX.XXX.XXX-XX

  @NotBlank(message = "CEP não pode ser vazio")
  @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX")
  private String cep; // CEP do cliente, deve seguir o formato XXXXX-XXX

  // Construtor padrão
  public ClienteDTO() {
  }
}
