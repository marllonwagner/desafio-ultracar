package com.ultracar.desafio_ultracar.service;
import com.ultracar.desafio_ultracar.dto.ClienteDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.entity.Endereco;
import com.ultracar.desafio_ultracar.entity.Veiculo;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import java.util.List;
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

  public Cliente salvarCliente(ClienteDTO clienteDto) {
    Cliente cliente = new Cliente();
    Endereco endereco = cepService.obterEnderecoPorCep(clienteDto.cep);
    cliente.setEndereco(endereco);
    cliente.setNome(clienteDto.nome);
    cliente.setCpf(clienteDto.cpf);
    return clienteRepository.save(cliente);
  }
}