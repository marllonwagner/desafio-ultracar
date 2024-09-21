package com.ultracar.desafio_ultracar.service;

import com.ultracar.desafio_ultracar.dto.VeiculoDTO;
import com.ultracar.desafio_ultracar.entity.Cliente;
import com.ultracar.desafio_ultracar.entity.Veiculo;
import com.ultracar.desafio_ultracar.repository.ClienteRepository;
import com.ultracar.desafio_ultracar.repository.VeiculoRepository;
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
    Veiculo veiculo = new Veiculo();
    veiculo.setMarca(veiculoDto.getMarca());
    veiculo.setModelo(veiculoDto.getModelo());
    veiculo.setAno(veiculoDto.getAno());
    veiculo.setPlaca(veiculoDto.getPlaca());


    Long clienteId = veiculoDto.getCliente_id();
    clienteRepository.findById(clienteId).orElseThrow(() ->
        new RuntimeException("Cliente não encontrado"));


    return veiculoRepository.save(veiculo);
  }
}
