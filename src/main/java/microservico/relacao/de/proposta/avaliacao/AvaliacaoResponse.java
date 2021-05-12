package microservico.relacao.de.proposta.avaliacao;

import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;
import microservico.relacao.de.proposta.proposta.Proposta;

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
	public AvaliacaoResponse (Avaliacao avaliacao) {
		documento = avaliacao.getDocumento();
		nome = avaliacao.getNome();
		idProposta = avaliacao.getProposta().getId();
		resultadoSolicitacao = avaliacao.getStatusDaAvaliacao();
	}
	
	public Avaliacao toModel(Proposta proposta) {
		return new Avaliacao(documento, nome, proposta, resultadoSolicitacao);
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
