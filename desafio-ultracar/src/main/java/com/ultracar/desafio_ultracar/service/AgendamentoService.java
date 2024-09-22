package com.ultracar.desafio_ultracar.service;

import com.ultracar.desafio_ultracar.dto.AgendamentoDTO;
import com.ultracar.desafio_ultracar.entity.Agendamento;
import com.ultracar.desafio_ultracar.entity.Agendamento.StatusAgendamento;
import com.ultracar.desafio_ultracar.exceptions.DataAgendamentoInvalidaException;
import com.ultracar.desafio_ultracar.repository.AgendamentoRepository;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

  private final AgendamentoRepository agendamentoRepository;
  private final ClienteRepository clienteRepository;

  @Autowired
  public AgendamentoService(AgendamentoRepository agendamentoRepository, ClienteRepository clienteRepository) {
    this.agendamentoRepository = agendamentoRepository;
    this.clienteRepository = clienteRepository;
  }

  public Agendamento criarAgendamento(AgendamentoDTO agendamentoDto) {
    LocalDateTime dataAgendamento = agendamentoDto.getDataAgendamento();

    if (dataAgendamento.isBefore(LocalDateTime.now())) {
      throw new DataAgendamentoInvalidaException("A data do agendamento não pode ser anterior à data atual.");
    }

    Agendamento agendamento = new Agendamento();
    agendamento.setClienteId(agendamentoDto.getClienteId());
    agendamento.setDataAgendamento(dataAgendamento);
    agendamento.setDescricaoServico(agendamentoDto.getDescricaoServico());
    agendamento.setStatus(StatusAgendamento.valueOf(agendamentoDto.getStatus().toUpperCase()));

    return agendamentoRepository.save(agendamento);
  }
  public List<Agendamento> listarAgendamentos() {
    return agendamentoRepository.findAll();
  }

  public List<Agendamento> buscarAgendamentosPorClienteId(Long clienteId) {
    return agendamentoRepository.findByClienteId(clienteId);
  }

  public Agendamento buscarAgendamentoPorId(Long id) {
    return agendamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
  }

  public Agendamento atualizarAgendamento(Long id, AgendamentoDTO agendamentoDto) {
    Agendamento agendamento = buscarAgendamentoPorId(id);
    agendamento.setDataAgendamento(agendamentoDto.getDataAgendamento());
    agendamento.setDescricaoServico(agendamentoDto.getDescricaoServico());
    agendamento.setStatus(StatusAgendamento.valueOf(agendamentoDto.getStatus().toUpperCase()));
    return agendamentoRepository.save(agendamento);
  }

  public List<Agendamento> buscarAgendamentosFiltrados(Long clienteId, StatusAgendamento status,
      LocalDateTime dataInicio, LocalDateTime dataFim) {
    if (status != null && dataInicio != null && dataFim != null) {
      return agendamentoRepository.findByClienteIdAndStatusAndDataAgendamentoBetween(clienteId,
          status, dataInicio, dataFim);
    } else if (status != null) {
      return agendamentoRepository.findByClienteIdAndStatus(clienteId, status);
    } else if (dataInicio != null && dataFim != null) {
      return agendamentoRepository.findByClienteIdAndDataAgendamentoBetween(clienteId,
          dataInicio, dataFim);
    } else {
      return agendamentoRepository.findByClienteId(clienteId);
    }
  }

  public Agendamento salvarAgendamento(Agendamento agendamento) {
    return agendamentoRepository.save(agendamento);
  }

  public void deletarAgendamento(Long id) {
    agendamentoRepository.deleteById(id);
  }
}
