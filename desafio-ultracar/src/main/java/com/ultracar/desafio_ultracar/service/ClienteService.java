package com.ultracar.desafio_ultracar.service;
import com.ultracar.desafio_ultracar.dto.ClienteDTO;
import com.ultracar.desafio_ultracar.dto.EnderecoDTO;
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

  public List<Cliente> listarClientes() {
    return clienteRepository.findAll();
  }

  public Cliente buscarClientePorId(Long id) {
    return clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
  }

  public Cliente atualizarCliente(Long id, ClienteDTO clienteDto) {
    Cliente cliente = buscarClientePorId(id);
    cliente.setNome(clienteDto.getNome());
    cliente.setCpf(clienteDto.getCpf());
    return clienteRepository.save(cliente);
  }

  public Cliente atualizarEnderecoCliente(Long id, EnderecoDTO enderecoDto) {
    Cliente cliente = buscarClientePorId(id);
    Endereco endereco = new Endereco();
    endereco.setBairro(enderecoDto.getBairro());
    endereco.setCep(enderecoDto.getCep());
    endereco.setEstado(enderecoDto.getEstado());
    endereco.setUf(enderecoDto.getUf());
    endereco.setLogradouro(enderecoDto.getLogradouro());
    endereco.setLocalidade(enderecoDto.getLocalidade());
    cliente.setEndereco(endereco);
    return clienteRepository.save(cliente);
  }


  public void deletarCliente(Long id) {
    clienteRepository.deleteById(id);
  }
}