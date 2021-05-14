package microservico.relacao.de.proposta;

import org.apache.tomcat.util.codec.binary.Base64;
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
		String message = "Fazendo um favor";
		byte[] encodeBase64 = Base64.encodeBase64(message.getBytes());
		
		System.out.println(encodeBase64);
		
		System.out.println(message);
		System.out.println(Base64.isBase64(message));
	}
}
