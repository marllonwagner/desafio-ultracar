package com.ultracar.desafio_ultracar.service;

import com.ultracar.desafio_ultracar.entity.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Serviço responsável pela integração com a API de consulta de endereços
 * via CEP. Este serviço permite obter informações de endereço com base
 * em um código postal fornecido.
 */
@Service
public class CepService {

  /**
   * Obtém um endereço a partir de um CEP fornecido.
   *
   * @param cep O código postal a ser consultado.
   * @return Um objeto Endereco contendo as informações obtidas da API.
   */
  public Endereco obterEnderecoPorCep(String cep) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://viacep.com.br/ws/" + cep + "/json/";

    return restTemplate.getForObject(url, Endereco.class);
  }
}
