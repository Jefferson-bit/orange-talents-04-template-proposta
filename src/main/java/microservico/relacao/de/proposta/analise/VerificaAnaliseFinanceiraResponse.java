package microservico.relacao.de.proposta.analise;

import microservico.relacao.de.proposta.enums.TipoDaSolicitacao;

public class VerificaAnaliseFinanceiraResponse {

	private String documento;
	private String nome;
	private Long idProposta;
	private TipoDaSolicitacao resultadoSolicitacao;

	public VerificaAnaliseFinanceiraResponse(String documento, String nome, Long idProposta,
			TipoDaSolicitacao resultadoSolicitacao) {
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
	
	public TipoDaSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}
