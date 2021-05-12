package microservico.relacao.de.proposta.avaliacao;

import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;

public class AvaliacaoRequest {

	private String documento;
	private String nome;
	private Long idProposta;
	private StatusDaAvaliacao resultadoSolicitacao;
	
	public AvaliacaoRequest() {
	}

	public AvaliacaoRequest(String documento, String nome, Long idProposta) {
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
