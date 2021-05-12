package microservico.relacao.de.proposta.proposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import microservico.relacao.de.proposta.enums.StatusDaProposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	@Query("SELECT obj FROM Proposta obj WHERE obj.numeroDoCartao IS NULL AND obj.statusDaProposta = :statusDaProposta")
	List<Proposta> buscaPropostasQueNaoPossuiNumeroDoCartao(StatusDaProposta statusDaProposta);
	Optional<String> findBydocumento(String cpfOuCnpj);
}
