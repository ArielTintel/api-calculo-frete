package br.com.arieltintel.calculofrete.service;

import br.com.arieltintel.calculofrete.client.ViaCepClient;
import br.com.arieltintel.calculofrete.dto.EnderecoViaCepResponseDTO;
import br.com.arieltintel.calculofrete.exception.EnderecoBadRequestException;
import br.com.arieltintel.calculofrete.exception.EnderecoNotFoundException;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EnderecoService {

    private final ViaCepClient viaCepClient;

    public EnderecoViaCepResponseDTO getEndereco(String cep) {
        try {
            final EnderecoViaCepResponseDTO enderecoViaCepResponseDTO = viaCepClient.getEndereco(cep);
            if (ObjectUtils.isEmpty(enderecoViaCepResponseDTO) || enderecoViaCepResponseDTO.getCep() == null) {
                throw new EnderecoNotFoundException(cep);
            }
            return enderecoViaCepResponseDTO;
        } catch (FeignException.BadRequest badRequest) {
            log.error("error: " + badRequest.getMessage());
            throw new EnderecoBadRequestException(cep);
        }
    }

}