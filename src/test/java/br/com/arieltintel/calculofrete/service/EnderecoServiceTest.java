package br.com.arieltintel.calculofrete.service;

import br.com.arieltintel.calculofrete.dto.EnderecoViaCepResponseDTO;
import br.com.arieltintel.calculofrete.client.ViaCepClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private ViaCepClient viaCepClient;

    private EnderecoViaCepResponseDTO enderecoViaCepResponseDTO;

    private static final String CEP = "41620190";

    @BeforeEach
    private void init() {

        enderecoViaCepResponseDTO = EnderecoViaCepResponseDTO.builder()
                .cep("41620190")
                .bairro("Itapuã")
                .localidade("Salvador")
                .complemento("Casa")
                .uf("BA")
                .logradouro("Rua do Palheta")
                .build();
    }

    @Test
    void getEnderecoTest() {

        when(viaCepClient.getEndereco(CEP)).thenReturn(this.enderecoViaCepResponseDTO);
        EnderecoViaCepResponseDTO enderecoViaCepResponseDTO = enderecoService.getEndereco(CEP);

        assertNotNull(enderecoViaCepResponseDTO);
        assertEquals(CEP, enderecoViaCepResponseDTO.getCep());
        assertEquals("Salvador", enderecoViaCepResponseDTO.getLocalidade());
    }

}