package microservico.relacao.de.proposta.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import microservico.relacao.de.proposta.proposta.AcompanhaPropostaController;

@Component
public class MinhaMetrica {

	private final MeterRegistry meterRegistry;

	public MinhaMetrica(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}

	private final Random random = new Random();
	private final Collection<String> strings = new ArrayList<>();

	// metrica de números de propostas criada
	public void meuContador() {
		Collection<Tag> tags = new ArrayList<>();
		tags.add(Tag.of("emissora", "Mastercard"));
		tags.add(Tag.of("banco", "Itaú"));

		Counter counter = this.meterRegistry.counter("proposta_criada", tags);
		counter.increment();

		Timer timer = this.meterRegistry.timer("consultar_proposta", tags);
		timer.record(() -> {
			AcompanhaPropostaController acompanhaPropostaController = new AcompanhaPropostaController(null);
			acompanhaPropostaController.timed();
		});

		this.meterRegistry.gauge("meu_gauge", tags, strings, Collection::size);
	}
	
	public void removeString() {
	    strings.removeIf(Objects::nonNull);
	}

	public void addString() {
	    strings.add(UUID.randomUUID().toString());
	}
	
	@Scheduled(fixedDelay = 1000)
	public void simulandoGauge() {
		System.out.println("Passando no scheduled");
	    double randomNumber = random.nextInt();
	    if (randomNumber % 2 == 0) {
	        addString();
	    } else {
	        removeString();
	    }
	}
}
