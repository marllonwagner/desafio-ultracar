package com.ultracar.desafio_ultracar.service;

import com.ultracar.desafio_ultracar.dto.VeiculoDTO;
import com.ultracar.desafio_ultracar.dto.VeiculoInfoDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.entity.Veiculo;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import com.ultracar.desafio_ultracar.repository.VeiculoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

  private final VeiculoRepository veiculoRepository;
  private final ClienteRepository clienteRepository;

  @Autowired
  public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository) {
    this.veiculoRepository = veiculoRepository;
    this.clienteRepository = clienteRepository;
  }

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
}
