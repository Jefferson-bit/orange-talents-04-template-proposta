package microservico.relacao.de.proposta.feignclient;

import javax.servlet.http.HttpServletRequest;

import microservico.relacao.de.proposta.bloqueio.Bloqueio;
import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.enums.StatusDoBloqueio;

public class BloqueioFeignResponse {
	
	private StatusDoBloqueio resultado;
	private String sistemaResponsavel;
	private String ipDoCliente;
	public BloqueioFeignResponse() {
	}
	
	public Bloqueio toModel(Cartao cartao, HttpServletRequest servletRequest) {
		sistemaResponsavel = servletRequest.getHeader("User-Agent");
		ipDoCliente = servletRequest.getRemoteAddr();
		return new Bloqueio(sistemaResponsavel, resultado, cartao, ipDoCliente);
	}
	
	public StatusDoBloqueio getResultado() {
		return resultado;
	}
}
