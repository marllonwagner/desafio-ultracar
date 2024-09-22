package com.ultracar.desafio_ultracar.repository;

import com.ultracar.desafio_ultracar.entity.Agendamento;
import com.ultracar.desafio_ultracar.entity.Agendamento.StatusAgendamento;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

  List<Agendamento> findByClienteId(Long clienteId);

  List<Agendamento> findByClienteIdAndStatus(Long clienteId, StatusAgendamento status);

  List<Agendamento> findByClienteIdAndDataAgendamentoBetween(Long clienteId,
      LocalDateTime dataInicio, LocalDateTime dataFim);

  List<Agendamento> findByClienteIdAndStatusAndDataAgendamentoBetween(Long clienteId,
      StatusAgendamento status, LocalDateTime dataInicio, LocalDateTime dataFim);
}
