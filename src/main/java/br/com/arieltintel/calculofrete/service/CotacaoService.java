package br.com.arieltintel.calculofrete.service;

import br.com.arieltintel.calculofrete.dto.CotacaoCalculadaResponseDTO;
import br.com.arieltintel.calculofrete.dto.CotacaoRequestDTO;
import br.com.arieltintel.calculofrete.dto.CotacaoResponseDTO;
import br.com.arieltintel.calculofrete.dto.EnderecoViaCepResponseDTO;
import br.com.arieltintel.calculofrete.repository.CotacaoRepository;
import br.com.arieltintel.calculofrete.entity.Cotacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CotacaoService {

    private final EnderecoService enderecoService;

    private final CotacaoRepository cotacaoRepository;

    private static final Long UM_DIA = 1L;
    private static final Long TRES_DIAS = 3L;
    private static final Long DEZ_DIAS = 10L;
    private static final BigDecimal ZERO_PORCENTO = BigDecimal.ZERO;
    private static final BigDecimal CINQUENTA_PORCENTO = new BigDecimal(0.5);
    private static final BigDecimal SETENTA_E_CINCO_PORCENTO = new BigDecimal(0.75);
    private BigDecimal desconto = BigDecimal.ZERO;
    private BigDecimal valorQuilo = BigDecimal.ONE;

    public CotacaoService(EnderecoService enderecoService, CotacaoRepository cotacaoRepository) {
        this.enderecoService = enderecoService;
        this.cotacaoRepository = cotacaoRepository;
    }

    public CotacaoResponseDTO obterCotacao(CotacaoRequestDTO cotacaoRequestDTO) {

        CotacaoResponseDTO cotacaoResponseDTO = CotacaoResponseDTO.builder()
                .cepOrigem(cotacaoRequestDTO.getCepOrigem())
                .cepDestino(cotacaoRequestDTO.getCepDestino())
                .build();

        BigDecimal valorTotalFrete = getValorTotalFrete(cotacaoRequestDTO);

        EnderecoViaCepResponseDTO enderecoOrigem = enderecoService.getEndereco(cotacaoRequestDTO.getCepOrigem());
        EnderecoViaCepResponseDTO enderecoDestino = enderecoService.getEndereco(cotacaoRequestDTO.getCepDestino());

        if (enderecoOrigem.getUf().equals(enderecoDestino.getUf())
                && enderecoOrigem.getDdd().equals(enderecoDestino.getDdd())) {
            setCotacao(cotacaoResponseDTO, valorTotalFrete, CINQUENTA_PORCENTO, UM_DIA);
        } else if (enderecoOrigem.getUf().equals(enderecoDestino.getUf())) {
            setCotacao(cotacaoResponseDTO, valorTotalFrete, SETENTA_E_CINCO_PORCENTO, TRES_DIAS);
        } else {
            setCotacao(cotacaoResponseDTO, valorTotalFrete, ZERO_PORCENTO, DEZ_DIAS);
        }

        cotacaoRepository.save(cotacaoResponseDTO.to(cotacaoRequestDTO));
        return cotacaoResponseDTO;

    }

    private void setCotacao(CotacaoResponseDTO cotacaoResponseDTO, BigDecimal valorTotalFrete, BigDecimal percentualDesconto, Long prazoEntrega) {
        desconto = valorTotalFrete.multiply(percentualDesconto);
        cotacaoResponseDTO.setValorTotalFrete(valorTotalFrete.subtract(desconto));
        cotacaoResponseDTO.setDataPrevistaEntrega(LocalDate.now().plusDays(prazoEntrega));

    }

    private BigDecimal getValorTotalFrete(CotacaoRequestDTO cotacaoRequestDTO) {
        return valorQuilo.multiply(cotacaoRequestDTO.getPresoItem());
    }

    public List<CotacaoCalculadaResponseDTO> getCotacaoList() {
        List<Cotacao> cotacaoList = cotacaoRepository.findAll();
        List<CotacaoCalculadaResponseDTO> cotacaoCalculadaResponseDTOList = new ArrayList<>();
        cotacaoList.stream().forEach(cotacao -> {
                    cotacaoCalculadaResponseDTOList.add(CotacaoCalculadaResponseDTO.builder()
                            .cepOrigem(cotacao.getCepOrigem())
                            .cepDestino(cotacao.getCepDestino())
                            .dataPrevistaEntrega(cotacao.getDataPrevistaEntrega())
                            .valorTotalFrete(cotacao.getValorTotalFrete())
                            .dataConsulta(cotacao.getDataConsulta())
                            .pesoItem(cotacao.getPesoItem())
                            .nomeDestinatario(cotacao.getNomeDestinatario())
                            .build());
                }
        );
        return cotacaoCalculadaResponseDTOList;
    }

}
