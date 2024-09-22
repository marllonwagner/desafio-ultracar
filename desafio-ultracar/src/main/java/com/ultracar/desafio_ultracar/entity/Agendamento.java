package com.ultracar.desafio_ultracar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Representa um agendamento no sistema.
 * Um agendamento é associado a um cliente e contém informações sobre a data,
 * a descrição do serviço e o estatus do agendamento.
 */
@Getter
@Setter
@Entity
@Table(name = "agendamentos")
public class Agendamento {

  /**
   * Identificador único do agendamento.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Identificador do cliente associado ao agendamento.
   */
  @Column(name = "cliente_id")
  private Long clienteId;

  /**
   * Data e hora do agendamento.
   */
  @Column(name = "data_agendamento")
  private LocalDateTime dataAgendamento;

  /**
   * Descrição do serviço a ser realizado no agendamento.
   */
  @Column(name = "descricao_servico")
  private String descricaoServico;

  /**
   * Status do agendamento.
   * Pode ser PENDENTE, REALIZADO ou CANCELADO.
   */
  @Enumerated(EnumType.STRING)
  private StatusAgendamento status;

  /**
   * Enum representando os possíveis estatus de um agendamento.
   */
  public enum StatusAgendamento {
    PENDENTE,
    REALIZADO,
    CANCELADO
  }
}
