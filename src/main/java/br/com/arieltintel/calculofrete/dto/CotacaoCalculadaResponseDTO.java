package br.com.arieltintel.calculofrete.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoCalculadaResponseDTO {

    private String nomeDestinatario;
    private BigDecimal valorTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private String cepOrigem;
    private String cepDestino;
    private LocalDate dataConsulta;
    private BigDecimal pesoItem;

}
