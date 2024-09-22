package com.ultracar.desafio_ultracar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) para agendamentos.
 * Utilizado para transferir dados entre a camada de apresentação e a camada de serviço.
 */
@Getter
@Setter
public class AgendamentoDTO {

  private Long id; // ID do agendamento
  private Long clienteId; // ID do cliente associado ao agendamento

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime dataAgendamento; // Data e hora do agendamento

  @NotBlank(message = "A descrição não pode ser vazia")
  private String descricaoServico; // Descrição do serviço a ser agendado

  @NotBlank(message = "Status não pode ser vazio")
  private String status; // Status do agendamento (PENDENTE, REALIZADO, CANCELADO)

  // Construtor padrão
  public AgendamentoDTO() {}
}
