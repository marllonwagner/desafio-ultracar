package com.ultracar.desafio_ultracar.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoDTO {

  private Long id;
  private Long clienteId;
  private LocalDateTime dataAgendamento;
  private String descricaoServico;
  private String status; // Usaremos String para facilitar a manipulação (PENDENTE, REALIZADO, CANCELADO)

  public AgendamentoDTO() {}
}
