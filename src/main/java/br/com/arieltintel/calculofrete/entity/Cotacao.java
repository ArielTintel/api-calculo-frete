package br.com.arieltintel.calculofrete.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cotacao")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "peso_item")
    private BigDecimal pesoItem;

    @Column(name = "nome_destinatario")
    private String nomeDestinatario;

    @Column(name = "valor_total_frete")
    private BigDecimal valorTotalFrete;

    @Column(name = "data_prevista_entrega")
    private LocalDate dataPrevistaEntrega;

    @Column(name = "cep_origem")
    private String cepOrigem;

    @Column(name = "cep_destino")
    private String cepDestino;

    @Column(name = "data_consulta")
    private LocalDate dataConsulta;


}
