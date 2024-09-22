package com.ultracar.desafio_ultracar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

  private Long id;

  @NotBlank(message = "Nome não pode ser vazio")
  private String nome;

  @NotBlank(message = "CPF não pode ser vazio")
  @Pattern(regexp = "^(?!(\\d)\\1{10})\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
      message = "CPF deve estar no formato XXX.XXX.XXX-XX")
  private String cpf;

  @NotBlank(message = "CEP não pode ser vazio")
  @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato XXXXX-XXX")
  private String cep;

  public ClienteDTO() {
  }
}
