package com.ultracar.desafio_ultracar.service;

import com.ultracar.desafio_ultracar.dto.VeiculoDTO;
import com.ultracar.desafio_ultracar.dto.VeiculoInfoDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.entity.Veiculo;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import com.ultracar.desafio_ultracar.repository.VeiculoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pela lógica de negócio relacionada aos veículos.
 * Inclui operações como adicionar, atualizar e deletar veículos.
 */
@Service
public class VeiculoService {

  private final VeiculoRepository veiculoRepository;
  private final ClienteRepository clienteRepository;

  /**
   * Construtor do serviço de veículos.
   *
   * @param veiculoRepository O repositório para operações de persistência de veículos.
   * @param clienteRepository O repositório para operações de persistência de clientes.
   */
  @Autowired
  public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
    this.veiculoRepository = veiculoRepository;
    this.clienteRepository = clienteRepository;
  }

  /**
   * Adiciona novos veículos para um cliente.
   *
   * @param veiculoDto Objeto contendo os dados dos veículos a serem adicionados.
   * @return null (a operação não retorna um veículo específico).
   * @throws RuntimeException Se o cliente associado não for encontrado.
   */
  public Veiculo addVeiculo(VeiculoDTO veiculoDto) {
    for (VeiculoInfoDTO veiculoInfo : veiculoDto.getVeiculos()) {
      Veiculo veiculo = new Veiculo();
      veiculo.setMarca(veiculoInfo.getMarca());
      veiculo.setModelo(veiculoInfo.getModelo());
      veiculo.setAno(veiculoInfo.getAno());
      veiculo.setPlaca(veiculoInfo.getPlaca());

      Long clienteId = veiculoDto.getCliente_id();
      Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
          new RuntimeException("Cliente não encontrado"));

      veiculo.setCliente(cliente);
      veiculoRepository.save(veiculo);
    }
    return null;
  }

  /**
   * Atualiza os dados de um veículo existente.
   *
   * @param id O ID do veículo a ser atualizado.
   * @param veiculoInfoDto Objeto contendo os novos dados do veículo.
   * @return O veículo atualizado.
   * @throws RuntimeException Se o veículo não for encontrado.
   */
  public Veiculo updateVeiculo(Long id, VeiculoInfoDTO veiculoInfoDto) {
    Veiculo veiculo = veiculoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    veiculo.setMarca(veiculoInfoDto.getMarca());
    veiculo.setModelo(veiculoInfoDto.getModelo());
    veiculo.setPlaca(veiculoInfoDto.getPlaca());
    veiculo.setAno(veiculoInfoDto.getAno());
    return veiculoRepository.save(veiculo);
  }

  /**
   * Deleta um veículo pelo seu ID.
   *
   * @param id O ID do veículo a ser deletado.
   */
  public void deleteVeiculo(Long id) {
    veiculoRepository.deleteById(id);
  }
}
