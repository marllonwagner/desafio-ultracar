package com.ultracar.desafio_ultracar.controller;

import com.ultracar.desafio_ultracar.dto.AgendamentoDTO;
import com.ultracar.desafio_ultracar.entity.Agendamento;
import com.ultracar.desafio_ultracar.entity.Agendamento.StatusAgendamento;
import com.ultracar.desafio_ultracar.service.AgendamentoService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela manipulação de agendamentos.
 * Este controlador fornece endpoints para criar, listar, buscar,
 * atualizar e deletar agendamentos.
 */
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

  private final AgendamentoService agendamentoService;

  @Autowired
  public AgendamentoController(AgendamentoService agendamentoService) {
    this.agendamentoService = agendamentoService;
  }

  /**
   * Cria um novo agendamento.
   *
   * @param agendamentoDto DTO contendo os dados do agendamento a ser criado.
   * @return ResponseEntity com o agendamento criado e status HTTP 201 (Created).
   */
  @PostMapping
  public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoDTO agendamentoDto) {
    Agendamento novoAgendamento = agendamentoService.criarAgendamento(agendamentoDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
  }

  /**
   * Lista todos os agendamentos.
   *
   * @return ResponseEntity com a lista de agendamentos e status HTTP 200 (OK).
   */
  @GetMapping
  public ResponseEntity<List<Agendamento>> listarAgendamentos() {
    List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
    return ResponseEntity.ok(agendamentos);
  }

  /**
   * Busca um agendamento pelo seu ID.
   *
   * @param id ID do agendamento a ser buscado.
   * @return ResponseEntity com o agendamento encontrado e status HTTP 200 (OK).
   */
  @GetMapping("/{id}")
  public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable Long id) {
    Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
    return ResponseEntity.ok(agendamento);
  }

  /**
   * Busca agendamentos filtrados por cliente, status e intervalo de datas.
   *
   * @param clienteId ID do cliente cujos agendamentos devem ser buscados.
   * @param status Status do agendamento (opcional).
   * @param dataInicio Data de início do intervalo (opcional).
   * @param dataFim Data de fim do intervalo (opcional).
   * @return ResponseEntity com a lista de agendamentos filtrados ou status HTTP 204 (No Content) se nenhum agendamento for encontrado.
   */
  @GetMapping("/cliente/{clienteId}")
  public ResponseEntity<List<Agendamento>> buscarAgendamentosFiltrados(
      @PathVariable Long clienteId,
      @RequestParam(required = false) StatusAgendamento status,
      @RequestParam(required = false) LocalDateTime dataInicio,
      @RequestParam(required = false) LocalDateTime dataFim) {

    List<Agendamento> agendamentos = agendamentoService.buscarAgendamentosFiltrados(clienteId, status, dataInicio, dataFim);

    if (agendamentos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(agendamentos);
  }

  /**
   * Atualiza um agendamento existente.
   *
   * @param id ID do agendamento a ser atualizado.
   * @param agendamentoDto DTO contendo os novos dados do agendamento.
   * @return ResponseEntity com o agendamento atualizado e status HTTP 200 (OK).
   */
  @PutMapping("/{id}")
  public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDto) {
    Agendamento agendamentoAtualizado = agendamentoService.atualizarAgendamento(id, agendamentoDto);
    return ResponseEntity.ok(agendamentoAtualizado);
  }

  /**
   * Deleta um agendamento pelo seu ID.
   *
   * @param id ID do agendamento a ser deletado.
   * @return ResponseEntity com status HTTP 204 (No Content) após a exclusão.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
    agendamentoService.deletarAgendamento(id);
    return ResponseEntity.noContent().build();
  }
}
