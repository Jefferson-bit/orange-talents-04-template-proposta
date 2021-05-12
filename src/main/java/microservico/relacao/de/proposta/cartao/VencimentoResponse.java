package microservico.relacao.de.proposta.cartao;

import java.time.LocalDateTime;

public class VencimentoResponse {

	private String id;
	private Integer dia;
	private LocalDateTime dataDeCriacao;

	public VencimentoResponse() {
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public Integer getDia() {
		return dia;
	}

	public String getId() {
		return id;
	}
}
