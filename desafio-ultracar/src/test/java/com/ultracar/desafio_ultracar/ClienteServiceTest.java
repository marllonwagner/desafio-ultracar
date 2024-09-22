package com.ultracar.desafio_ultracar;

import com.ultracar.desafio_ultracar.dto.ClienteDTO;
import com.ultracar.desafio_ultracar.dto.EnderecoDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.entity.Endereco;
import com.ultracar.desafio_ultracar.service.ClienteService;
import com.ultracar.desafio_ultracar.service.CepService;
import com.ultracar.desafio_ultracar.exceptions.CpfDuplicadoException;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

  @InjectMocks
  private ClienteService clienteService;

  @Mock
  private ClienteRepository clienteRepository;

  @Mock
  private CepService cepService;

  private ClienteDTO clienteDto;
  private Cliente cliente;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);  // Inicializa os mocks

    // Configuração do ClienteDTO
    clienteDto = new ClienteDTO();
    clienteDto.setNome("João");
    clienteDto.setCpf("12345678909");
    clienteDto.setCep("12345678");

    // Configuração do Cliente
    cliente = new Cliente();
    cliente.setNome(clienteDto.getNome());
    cliente.setCpf(clienteDto.getCpf());

    // Inicialização do Endereço
    Endereco endereco = new Endereco();
    endereco.setBairro("Bairro Exemplo");
    endereco.setCep(clienteDto.getCep());
    endereco.setEstado("Estado Exemplo");
    endereco.setUf("UF");
    endereco.setLogradouro("Logradouro Exemplo");
    endereco.setLocalidade("Localidade Exemplo");

    cliente.setEndereco(endereco);

    // Configuração do mock do cepService
    when(cepService.obterEnderecoPorCep(clienteDto.getCep())).thenReturn(endereco);
  }

  @Test
  void salvarCliente_Success() {
    when(clienteRepository.existsByCpf(clienteDto.getCpf())).thenReturn(false);
    when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

    Cliente savedCliente = clienteService.salvarCliente(clienteDto);

    assertNotNull(savedCliente);
    assertEquals(clienteDto.getNome(), savedCliente.getNome());
    assertEquals(clienteDto.getCpf(), savedCliente.getCpf());
  }
  @Test
  void listarClientes() {
    when(clienteRepository.findAll()).thenReturn(Collections.singletonList(cliente));

    var clientes = clienteService.listarClientes();

    assertFalse(clientes.isEmpty());
    assertEquals(1, clientes.size());
    assertEquals(cliente.getNome(), clientes.get(0).getNome());
  }

  @Test
  void buscarClientePorId_NotFound() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      clienteService.buscarClientePorId(1L);
    });

    assertEquals("Cliente não encontrado", exception.getMessage());
  }

  @Test
  void buscarClientePorId_Success() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

    Cliente result = clienteService.buscarClientePorId(1L);

    assertEquals(cliente.getNome(), result.getNome());
  }

  @Test
  void atualizarCliente_Success() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
    when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

    Cliente updatedCliente = clienteService.atualizarCliente(1L, clienteDto);

    assertEquals(cliente.getNome(), updatedCliente.getNome());
    verify(clienteRepository).save(any(Cliente.class));
  }

  @Test
  void atualizarEnderecoCliente_Success() {
    EnderecoDTO enderecoDto = new EnderecoDTO();
    enderecoDto.setBairro("Centro");
    enderecoDto.setCep("12345678");
    enderecoDto.setEstado("RJ");
    enderecoDto.setLogradouro("Rua A");
    enderecoDto.setLocalidade("Rio de Janeiro");

    when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
    when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

    Cliente updatedCliente = clienteService.atualizarEnderecoCliente(1L, enderecoDto);

    assertEquals(enderecoDto.getBairro(), updatedCliente.getEndereco().getBairro());
    verify(clienteRepository).save(any(Cliente.class));
  }

  @Test
  void deletarCliente_Success() {
    doNothing().when(clienteRepository).deleteById(1L);

    clienteService.deletarCliente(1L);

    verify(clienteRepository).deleteById(1L);
  }
}