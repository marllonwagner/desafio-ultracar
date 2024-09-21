package com.ultracar.desafio_ultracar.service;
import com.ultracar.desafio_ultracar.entity.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

  public Endereco obterEnderecoPorCep(String cep) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://viacep.com.br/ws/" + cep + "/json/";

    return restTemplate.getForObject(url, Endereco.class);
  }
}
