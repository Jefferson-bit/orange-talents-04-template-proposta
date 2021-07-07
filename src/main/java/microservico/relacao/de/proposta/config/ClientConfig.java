package microservico.relacao.de.proposta.config;

import feign.codec.ErrorDecoder;
import microservico.relacao.de.proposta.feignclient.CustomFeignException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomFeignException();
    }
}
