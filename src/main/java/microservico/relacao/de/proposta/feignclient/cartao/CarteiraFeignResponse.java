package microservico.relacao.de.proposta.feignclient.cartao;

import microservico.relacao.de.proposta.enums.StatusDaCarteira;

public class CarteiraFeignResponse {
	private StatusDaCarteira resultado;
	private String id;

	public String getId() {
		return id;
	}

	public StatusDaCarteira getResultado() {
		return resultado;
	}
}
