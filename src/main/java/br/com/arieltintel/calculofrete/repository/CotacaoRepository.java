package br.com.arieltintel.calculofrete.repository;

import br.com.arieltintel.calculofrete.entity.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

}
