package microservico.relacao.de.proposta.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import microservico.relacao.de.proposta.enums.StatusDaProposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	
	@Query( "SELECT obj FROM Proposta obj WHERE obj.cartao IS NULL "
			+ "AND obj.resultadoSolicitacao = :resultadoSolicitacao")
	List<Proposta> buscaPropostasQueNaoPossuiNumeroDoCartao(StatusDaProposta resultadoSolicitacao);
}
