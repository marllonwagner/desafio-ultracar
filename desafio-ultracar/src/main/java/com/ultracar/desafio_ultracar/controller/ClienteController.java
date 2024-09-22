package com.ultracar.desafio_ultracar.controller;

import com.ultracar.desafio_ultracar.dto.ClienteDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteService clienteService;

  @Autowired
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @PostMapping
  public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteDTO clienteDto) {
    Cliente novoCliente = clienteService.salvarCliente(clienteDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> listarClientes() {
    List<Cliente> clientes = clienteService.listarClientes();
    return ResponseEntity.ok(clientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
    Cliente cliente = clienteService.buscarClientePorId(id);
    return ResponseEntity.ok(cliente);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDto) {
    Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteDto);
    return ResponseEntity.ok(clienteAtualizado);
  }

  @PutMapping("/{id}/endereco")
  public ResponseEntity<Cliente> atualizarEnderecoCliente(@PathVariable Long id,
      @RequestBody ClienteDTO clienteDto) {
    Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteDto);
    return ResponseEntity.ok(clienteAtualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
    clienteService.deletarCliente(id);
    return ResponseEntity.noContent().build();
  }
}
