package microservico.relacao.de.proposta.cartao;

public class CartaoRequest {

	private String documento;
	private String nome;
	private Long idProposta;

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
