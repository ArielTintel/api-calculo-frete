package br.com.arieltintel.calculofrete.dto;

import br.com.arieltintel.calculofrete.entity.Cotacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoResponseDTO {

    private BigDecimal valorTotalFrete;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataPrevistaEntrega;
    private String cepOrigem;
    private String cepDestino;

    public Cotacao to(CotacaoRequestDTO cotacaoRequestDTO){
       return Cotacao.builder()
                .cepOrigem(this.getCepOrigem())
                .cepDestino(this.getCepDestino())
                .dataConsulta(LocalDate.now())
                .valorTotalFrete(this.valorTotalFrete)
                .pesoItem(cotacaoRequestDTO.getPresoItem())
                .dataPrevistaEntrega(this.getDataPrevistaEntrega())
                .nomeDestinatario(cotacaoRequestDTO.getNomeDestinatario())
                .build();
    }

}
