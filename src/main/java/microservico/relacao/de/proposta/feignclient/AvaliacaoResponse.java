package microservico.relacao.de.proposta.feignclient;

import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;
import microservico.relacao.de.proposta.enums.StatusDaProposta;

public class AvaliacaoResponse {

	private String documento;
	private String nome;
	private Long idProposta;
	private StatusDaAvaliacao resultadoSolicitacao;

	public AvaliacaoResponse() {
	}

	public AvaliacaoResponse(String documento, String nome, Long idProposta, StatusDaAvaliacao resultadoSolicitacao) {
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
