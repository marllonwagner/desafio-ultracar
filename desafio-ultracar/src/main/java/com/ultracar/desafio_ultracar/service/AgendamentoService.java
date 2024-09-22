package com.ultracar.desafio_ultracar.service;

import com.ultracar.desafio_ultracar.dto.AgendamentoDTO;
import com.ultracar.desafio_ultracar.entity.Agendamento;
import com.ultracar.desafio_ultracar.entity.Agendamento.StatusAgendamento;
import com.ultracar.desafio_ultracar.exceptions.DataAgendamentoInvalidaException;
import com.ultracar.desafio_ultracar.repository.AgendamentoRepository;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela lógica de negócios relacionada aos agendamentos.
 * Este serviço fornece métodos para criar, listar, atualizar e deletar agendamentos.
 */
@Service
public class AgendamentoService {

  private final AgendamentoRepository agendamentoRepository;
  private final ClienteRepository clienteRepository;

  @Autowired
  public AgendamentoService(AgendamentoRepository agendamentoRepository, ClienteRepository clienteRepository) {
    this.agendamentoRepository = agendamentoRepository;
    this.clienteRepository = clienteRepository;
  }

  /**
   * Cria um novo agendamento.
   *
   * @param agendamentoDto DTO contendo os dados do agendamento a ser criado.
   * @return O agendamento criado.
   * @throws DataAgendamentoInvalidaException se a data do agendamento for anterior à data atual.
   */
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

  /**
   * Lista todos os agendamentos.
   *
   * @return Lista de todos os agendamentos.
   */
  public List<Agendamento> listarAgendamentos() {
    return agendamentoRepository.findAll();
  }

  /**
   * Busca agendamentos por ID de cliente.
   *
   * @param clienteId ID do cliente cujos agendamentos devem ser buscados.
   * @return Lista de agendamentos do cliente.
   */
  public List<Agendamento> buscarAgendamentosPorClienteId(Long clienteId) {
    return agendamentoRepository.findByClienteId(clienteId);
  }

  /**
   * Busca um agendamento por ID.
   *
   * @param id ID do agendamento a ser buscado.
   * @return O agendamento correspondente ao ID fornecido.
   * @throws RuntimeException se o agendamento não for encontrado.
   */
  public Agendamento buscarAgendamentoPorId(Long id) {
    return agendamentoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
  }

  /**
   * Atualiza um agendamento existente.
   *
   * @param id ID do agendamento a ser atualizado.
   * @param agendamentoDto DTO contendo os novos dados do agendamento.
   * @return O agendamento atualizado.
   */
  public Agendamento atualizarAgendamento(Long id, AgendamentoDTO agendamentoDto) {
    Agendamento agendamento = buscarAgendamentoPorId(id);
    agendamento.setDataAgendamento(agendamentoDto.getDataAgendamento());
    agendamento.setDescricaoServico(agendamentoDto.getDescricaoServico());
    agendamento.setStatus(StatusAgendamento.valueOf(agendamentoDto.getStatus().toUpperCase()));
    return agendamentoRepository.save(agendamento);
  }

  /**
   * Busca agendamentos filtrados por cliente, status e intervalo de datas.
   *
   * @param clienteId ID do cliente.
   * @param status Status do agendamento.
   * @param dataInicio Data de início do filtro.
   * @param dataFim Data de fim do filtro.
   * @return Lista de agendamentos filtrados.
   */
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

  /**
   * Salva um agendamento.
   *
   * @param agendamento O agendamento a ser salvo.
   * @return O agendamento salvo.
   */
  public Agendamento salvarAgendamento(Agendamento agendamento) {
    return agendamentoRepository.save(agendamento);
  }

  /**
   * Deleta um agendamento pelo seu ID.
   *
   * @param id ID do agendamento a ser deletado.
   */
  public void deletarAgendamento(Long id) {
    agendamentoRepository.deleteById(id);
  }
}
