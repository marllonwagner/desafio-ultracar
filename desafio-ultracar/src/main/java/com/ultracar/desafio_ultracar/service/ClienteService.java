package com.ultracar.desafio_ultracar.service;

import com.ultracar.desafio_ultracar.dto.ClienteDTO;
import com.ultracar.desafio_ultracar.dto.EnderecoDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.entity.Endereco;
import com.ultracar.desafio_ultracar.exceptions.CpfDuplicadoException;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pela lógica de negócio relacionada aos clientes.
 * Inclui operações como salvar, listar, buscar, atualizar e deletar clientes.
 */
@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;

  @Autowired
  private CepService cepService;

  /**
   * Construtor do serviço de cliente.
   *
   * @param clienteRepository O repositório para operações de persistência de clientes.
   */
  @Autowired
  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  /**
   * Salva um novo cliente no sistema.
   *
   * @param clienteDto Objeto contendo os dados do cliente a serem salvos.
   * @return O cliente salvo.
   * @throws CpfDuplicadoException Se o CPF já estiver cadastrado.
   */
  public Cliente salvarCliente(ClienteDTO clienteDto) {
    if (clienteRepository.existsByCpf(clienteDto.getCpf())) {
      throw new CpfDuplicadoException("CPF já cadastrado!"); // Lança uma exceção se o CPF existir
    }
    Cliente cliente = new Cliente();
    Endereco endereco = cepService.obterEnderecoPorCep(clienteDto.getCep());
    cliente.setEndereco(endereco);
    cliente.setNome(clienteDto.getNome());
    cliente.setCpf(clienteDto.getCpf());
    return clienteRepository.save(cliente);
  }

  /**
   * Lista todos os clientes cadastrados no sistema.
   *
   * @return Uma lista de clientes.
   */
  public List<Cliente> listarClientes() {
    return clienteRepository.findAll();
  }

  /**
   * Busca um cliente pelo seu ID.
   *
   * @param id O ID do cliente a ser buscado.
   * @return O cliente encontrado.
   * @throws RuntimeException Se o cliente não for encontrado.
   */
  public Cliente buscarClientePorId(Long id) {
    return clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
  }

  /**
   * Atualiza os dados de um cliente existente.
   *
   * @param id O ID do cliente a ser atualizado.
   * @param clienteDto Objeto contendo os novos dados do cliente.
   * @return O cliente atualizado.
   */
  public Cliente atualizarCliente(Long id, ClienteDTO clienteDto) {
    Cliente cliente = buscarClientePorId(id);
    cliente.setNome(clienteDto.getNome());
    cliente.setCpf(clienteDto.getCpf());
    return clienteRepository.save(cliente);
  }

  /**
   * Atualiza o endereço de um cliente existente.
   *
   * @param id O ID do cliente cujo endereço será atualizado.
   * @param enderecoDto Objeto contendo os novos dados do endereço.
   * @return O cliente com o endereço atualizado.
   */
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

  /**
   * Deleta um cliente pelo seu ID.
   *
   * @param id O ID do cliente a ser deletado.
   */
  public void deletarCliente(Long id) {
    clienteRepository.deleteById(id);
  }
}
