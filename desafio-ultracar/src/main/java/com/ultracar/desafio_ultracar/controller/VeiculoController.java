package com.ultracar.desafio_ultracar.controller;

import com.ultracar.desafio_ultracar.dto.VeiculoDTO;
import com.ultracar.desafio_ultracar.entity.Veiculo;
import com.ultracar.desafio_ultracar.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  private final VeiculoService veiculoService;

  @Autowired
  public VeiculoController(VeiculoService veiculoService) {
    this.veiculoService = veiculoService;
  }

  @PostMapping
  public ResponseEntity<Veiculo> addVeiculo(@RequestBody VeiculoDTO veiculoDto) {
    Veiculo novoVeiculo = veiculoService.addVeiculo(veiculoDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
  }

}
