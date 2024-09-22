package com.ultracar.desafio_ultracar.controller;

import com.ultracar.desafio_ultracar.dto.ClienteDTO;
import com.ultracar.desafio_ultracar.dto.EnderecoDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela manipulação de clientes.
 * Este controlador fornece endpoints para criar, listar, buscar,
 * atualizar e deletar clientes.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteService clienteService;

  @Autowired
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  /**
   * Cria um novo cliente.
   *
   * @param clienteDto DTO contendo os dados do cliente a ser criado.
   * @return ResponseEntity com o cliente criado e status HTTP 201 (Created).
   */
  @PostMapping
  public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody ClienteDTO clienteDto) {
    Cliente novoCliente = clienteService.salvarCliente(clienteDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
  }

  /**
   * Lista todos os clientes.
   *
   * @return ResponseEntity com a lista de clientes e status HTTP 200 (OK).
   */
  @GetMapping
  public ResponseEntity<List<Cliente>> listarClientes() {
    List<Cliente> clientes = clienteService.listarClientes();
    return ResponseEntity.ok(clientes);
  }

  /**
   * Busca um cliente pelo seu ID.
   *
   * @param id ID do cliente a ser buscado.
   * @return ResponseEntity com o cliente encontrado e status HTTP 200 (OK).
   */
  @GetMapping("/{id}")
  public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
    Cliente cliente = clienteService.buscarClientePorId(id);
    return ResponseEntity.ok(cliente);
  }

  /**
   * Atualiza um cliente existente.
   *
   * @param id ID do cliente a ser atualizado.
   * @param clienteDto DTO contendo os novos dados do cliente.
   * @return ResponseEntity com o cliente atualizado e status HTTP 200 (OK).
   */
  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id,
      @Valid @RequestBody ClienteDTO clienteDto) {
    Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteDto);
    return ResponseEntity.ok(clienteAtualizado);
  }

  /**
   * Atualiza o endereço de um cliente existente.
   *
   * @param id ID do cliente cujo endereço deve ser atualizado.
   * @param enderecoDto DTO contendo os novos dados do endereço.
   * @return ResponseEntity com o cliente atualizado e status HTTP 200 (OK).
   */
  @PutMapping("/{id}/endereco")
  public ResponseEntity<Cliente> atualizarEnderecoCliente(@PathVariable Long id,
      @RequestBody EnderecoDTO enderecoDto) {
    Cliente enderecoAtualizado = clienteService.atualizarEnderecoCliente(id, enderecoDto);
    return ResponseEntity.ok(enderecoAtualizado);
  }

  /**
   * Deleta um cliente pelo seu ID.
   *
   * @param id ID do cliente a ser deletado.
   * @return ResponseEntity com status HTTP 204 (No Content) após a exclusão.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
    clienteService.deletarCliente(id);
    return ResponseEntity.noContent().build();
  }
}
