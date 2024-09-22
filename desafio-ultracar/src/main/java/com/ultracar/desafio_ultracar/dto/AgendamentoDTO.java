package com.ultracar.desafio_ultracar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoDTO {

  private Long id;
  private Long clienteId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime dataAgendamento;
  @NotBlank(message = "A descrição não pode ser vazia")
  private String descricaoServico;
  @NotBlank(message = "status não pode ser vazio")
  private String status; // Usaremos String para facilitar a manipulação (PENDENTE, REALIZADO, CANCELADO)

  public AgendamentoDTO() {}
}
