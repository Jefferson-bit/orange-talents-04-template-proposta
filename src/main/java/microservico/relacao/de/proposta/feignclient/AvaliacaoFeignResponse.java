package microservico.relacao.de.proposta.feignclient;

import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;
import microservico.relacao.de.proposta.enums.StatusDaProposta;

public class AvaliacaoFeignResponse {

	private String documento;
	private String nome;
	private Long idProposta;
	private StatusDaAvaliacao resultadoSolicitacao;

	public AvaliacaoFeignResponse() {
	}

	public AvaliacaoFeignResponse(String documento, String nome, Long idProposta, StatusDaAvaliacao resultadoSolicitacao) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public StatusDaAvaliacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	
	public StatusDaProposta status() {
		return resultadoSolicitacao.getStatus();
	}
}
