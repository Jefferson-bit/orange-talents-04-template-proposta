package microservico.relacao.de.proposta.feignclient;

import microservico.relacao.de.proposta.enums.StatusDoAviso;

public class AvisoFeignResponse {
	
	private StatusDoAviso resultado;
	
	public StatusDoAviso getResultado() {
		return resultado;	
	}
}
