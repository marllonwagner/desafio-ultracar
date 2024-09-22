package com.ultracar.desafio_ultracar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "agendamentos")
public class Agendamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "cliente_id")
  private Long clienteId;

  @Column(name = "data_agendamento")
  private LocalDateTime dataAgendamento;

  @Column(name = "descricao_servico")
  private String descricaoServico;

  @Enumerated(EnumType.STRING)
  private StatusAgendamento status;

  public enum StatusAgendamento {
    PENDENTE,
    REALIZADO,
    CANCELADO
  }
}
