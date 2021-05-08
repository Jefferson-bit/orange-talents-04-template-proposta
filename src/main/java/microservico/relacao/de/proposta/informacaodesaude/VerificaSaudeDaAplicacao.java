package microservico.relacao.de.proposta.informacaodesaude;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class VerificaSaudeDaAplicacao implements HealthIndicator {
	
	/*
	 * Classe ThreadLocalRandom gera numeros aleátorios em ambientes multi-threads
	 * Caso o número aleátorio gerado for acima de 9, o health retorna o status down
	 */
	@Override
	public Health health() {
		double chance = ThreadLocalRandom.current().nextDouble();
		Health.Builder status = Health.up();
		if (chance > 0.9) {
			status = Health.down();
		}
		return status.build();
	}
}