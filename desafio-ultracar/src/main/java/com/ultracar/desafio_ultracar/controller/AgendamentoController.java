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

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

  private final AgendamentoService agendamentoService;

  @Autowired
  public AgendamentoController(AgendamentoService agendamentoService) {
    this.agendamentoService = agendamentoService;
  }

  @PostMapping
  public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoDTO agendamentoDto) {
    Agendamento novoAgendamento = agendamentoService.criarAgendamento(agendamentoDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
  }

  @GetMapping
  public ResponseEntity<List<Agendamento>> listarAgendamentos() {
    List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
    return ResponseEntity.ok(agendamentos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable Long id) {
    Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(id);
    return ResponseEntity.ok(agendamento);
  }

  @GetMapping("/cliente/{clienteId}")
  public ResponseEntity<List<Agendamento>> buscarAgendamentosFiltrados(
      @PathVariable Long clienteId,
      @RequestParam(required = false) StatusAgendamento status,
      @RequestParam(required = false) LocalDateTime dataInicio,
      @RequestParam(required = false) LocalDateTime dataFim) {

    List<Agendamento> agendamentos = agendamentoService.buscarAgendamentosFiltrados(clienteId,
        status, dataInicio, dataFim);

    if (agendamentos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(agendamentos);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDto) {
    Agendamento agendamentoAtualizado = agendamentoService.atualizarAgendamento(id, agendamentoDto);
    return ResponseEntity.ok(agendamentoAtualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
    agendamentoService.deletarAgendamento(id);
    return ResponseEntity.noContent().build();
  }
}
