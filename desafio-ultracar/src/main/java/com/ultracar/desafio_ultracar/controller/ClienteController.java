package com.ultracar.desafio_ultracar.controller;
import com.ultracar.desafio_ultracar.dto.ClienteDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteService clienteService;

  @Autowired
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @PostMapping
  public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente,@RequestParam String cep) {
    Cliente novoCliente = clienteService.salvarCliente(cliente,cep);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
  }


}