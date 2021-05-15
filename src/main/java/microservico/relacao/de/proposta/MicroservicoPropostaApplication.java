package microservico.relacao.de.proposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class MicroservicoPropostaApplication {	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicoPropostaApplication.class, args);
	}
}
