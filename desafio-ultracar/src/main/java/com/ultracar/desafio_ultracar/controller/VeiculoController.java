package com.ultracar.desafio_ultracar.controller;

import com.ultracar.desafio_ultracar.dto.VeiculoDTO;
import com.ultracar.desafio_ultracar.dto.VeiculoInfoDTO;
import com.ultracar.desafio_ultracar.entity.Veiculo;
import com.ultracar.desafio_ultracar.service.VeiculoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pela manipulação de veículos.
 * Este controlador fornece endpoints para adicionar, atualizar e deletar veículos.
 */
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  private final VeiculoService veiculoService;

  @Autowired
  public VeiculoController(VeiculoService veiculoService) {
    this.veiculoService = veiculoService;
  }

  /**
   * Adiciona um novo veículo.
   *
   * @param veiculoDto DTO contendo os dados do veículo a ser adicionado.
   * @return ResponseEntity com a lista de veículos adicionados e status HTTP 201 (Created).
   */
  @PostMapping
  public ResponseEntity<List<Veiculo>> addVeiculos(@Valid @RequestBody VeiculoDTO veiculoDto) {
    List<Veiculo> novosVeiculos = (List<Veiculo>) veiculoService.addVeiculo(veiculoDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(novosVeiculos);
  }

  /**
   * Atualiza um veículo existente.
   *
   * @param id ID do veículo a ser atualizado.
   * @param veiculoInfoDto DTO contendo os novos dados do veículo.
   * @return ResponseEntity com o veículo atualizado e status HTTP 200 (OK).
   */
  @PutMapping("/{id}")
  public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id,
      @Valid @RequestBody VeiculoInfoDTO veiculoInfoDto) {
    Veiculo veiculoAtualizado = veiculoService.updateVeiculo(id, veiculoInfoDto);
    return ResponseEntity.ok(veiculoAtualizado);
  }

  /**
   * Deleta um veículo pelo seu ID.
   *
   * @param id ID do veículo a ser deletado.
   * @return ResponseEntity com status HTTP 204 (No Content) após a exclusão.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
    veiculoService.deleteVeiculo(id);
    return ResponseEntity.noContent().build();
  }

}
