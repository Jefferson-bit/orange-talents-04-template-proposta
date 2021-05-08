package microservico.relacao.de.proposta.analise;

public class VerificaAnaliseFinanceiraRequest {

	private String documento;
	private String nome;
	private Long idProposta;

	public VerificaAnaliseFinanceiraRequest(String documento, String nome, Long idProposta) {
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
}
