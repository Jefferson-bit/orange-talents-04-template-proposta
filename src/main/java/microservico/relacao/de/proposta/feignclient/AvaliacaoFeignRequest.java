package microservico.relacao.de.proposta.feignclient;

import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;

public class AvaliacaoFeignRequest {

	private String documento;
	private String nome;
	private Long idProposta;
	private StatusDaAvaliacao resultadoSolicitacao;
	
	public AvaliacaoFeignRequest() {
	}

	public AvaliacaoFeignRequest(String documento, String nome, Long idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
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
}
