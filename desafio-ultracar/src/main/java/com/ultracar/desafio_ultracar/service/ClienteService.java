package com.ultracar.desafio_ultracar.service;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.entity.Endereco;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;

  @Autowired
  private CepService cepService;
  @Autowired
  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public Cliente salvarCliente(Cliente cliente, String cep) {
    Endereco endereco = cepService.obterEnderecoPorCep(cep);
    cliente.setEndereco(endereco);
    return clienteRepository.save(cliente);
  }
}