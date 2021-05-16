package microservico.relacao.de.proposta.feignclient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.proposta.Proposta;

public class CartaoFeignResponse {

	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	@NotNull(message = "Proposta não pode ser nula")
	private Long idProposta;

	public CartaoFeignResponse() {
	}

	public Cartao toModel(Proposta proposta) {
		return new Cartao(id, emitidoEm, titular, limite, proposta);
	}

	public Cartao toModel() {
		return new Cartao(id, emitidoEm, titular, limite);
	}
	
	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getId() {
		return id;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public String getTitular() {
		return titular;
	}

}
