package br.com.arieltintel.calculofrete.client;

import br.com.arieltintel.calculofrete.dto.EnderecoViaCepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    EnderecoViaCepResponseDTO getEndereco(@PathVariable String cep);

}
