package microservico.relacao.de.proposta.feignclient;

import microservico.relacao.de.proposta.bloqueio.Bloqueio;
import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.enums.StatusDoBloqueio;

public class BloqueioFeignResponse {
	
	private StatusDoBloqueio resultado;;
	private String sistemaResponsavel;
	
	public BloqueioFeignResponse() {
	}
	
	public Bloqueio toModel(Cartao cartao) {
		return new Bloqueio(sistemaResponsavel, resultado, cartao);
	}
	
	public StatusDoBloqueio getResultado() {
		return resultado;
	}
	
	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}
}
