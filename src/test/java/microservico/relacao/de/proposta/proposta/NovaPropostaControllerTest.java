package microservico.relacao.de.proposta.proposta;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFeignRequest;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFeignResponse;
import microservico.relacao.de.proposta.feignclient.avaliacao.AvaliacaoFinanceiraFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.net.URI;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class NovaPropostaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PropostaRepository propostaRepository;

    @MockBean
    AvaliacaoFinanceiraFeignClient avaliacao;

    @Test
    void deveriaRegistrarUmaNovaPropostaComStatus201Created() throws Exception {
        NovaPropostaRequest propostaRequest = new NovaPropostaRequest(
                "rodrigoo@gmail.com",
                "Rodrigo Oliveira",
                "Via Lactea",
                new BigDecimal(10.0),
                "58159011059");

        Mockito.when(avaliacao.consultandoAnalise(
        new AvaliacaoFeignRequest(propostaRequest.getDocumento(), propostaRequest.getNome(), 1L)))
        .thenReturn(
        new AvaliacaoFeignResponse(propostaRequest.getDocumento(), propostaRequest.getNome(), 1L,
        StatusDaAvaliacao.SEM_RESTRICAO));

        String jsonBody = objectMapper.writeValueAsString(propostaRequest);

        mockMvc.perform(post("/api/propostas")
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_proposta-scope")))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/api/propostas/1"));
    }

    @Test
    void deveriaRetornar422QuandoOsDadosForemInvalido() throws Exception {
        NovaPropostaRequest propostaRequest = new NovaPropostaRequest
                ("", "", "", new BigDecimal(0), "");

        String jsonBody = objectMapper.writeValueAsString(propostaRequest);

        mockMvc.perform(post("/api/propostas")
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_proposta-scope")))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deveriaRetornar422QuandoOEmailForDuplicado() throws Exception {

        Proposta proposta = new Proposta("rodrigoo@gmail.com",
                "Rodrigo Oliveira",
                "Via Lactea",
                new BigDecimal(10.0),
                "58159011059");

        propostaRepository.save(proposta);

        NovaPropostaRequest propostaRequest = new NovaPropostaRequest(
                "rodrigoo@gmail.com",
                "Rodrigo Oliveira",
                "Via Lactea",
                new BigDecimal(10.0),
                "58159011059");

        Mockito.when(avaliacao.consultandoAnalise(
        new AvaliacaoFeignRequest(propostaRequest.getDocumento(), propostaRequest.getNome(), 1L)))
        .thenReturn(
        new AvaliacaoFeignResponse(propostaRequest.getDocumento(), propostaRequest.getNome(), 1L,
        StatusDaAvaliacao.SEM_RESTRICAO));

        String jsonBody = objectMapper.writeValueAsString(propostaRequest);

        mockMvc.perform(post("/api/propostas")
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_proposta-scope")))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isUnprocessableEntity());
    }
}


