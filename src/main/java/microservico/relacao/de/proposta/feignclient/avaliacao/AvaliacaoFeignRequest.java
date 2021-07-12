package microservico.relacao.de.proposta.feignclient.avaliacao;

import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;
import microservico.relacao.de.proposta.proposta.Proposta;

import java.util.Objects;

public class AvaliacaoFeignRequest {

	private String documento;
	private String nome;
	private Long idProposta;

	public AvaliacaoFeignRequest() {
	}

	public AvaliacaoFeignRequest(String documento, String nome, Long idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public AvaliacaoFeignRequest(Proposta proposta){
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AvaliacaoFeignRequest that = (AvaliacaoFeignRequest) o;
		return Objects.equals(documento, that.documento) && Objects.equals(nome, that.nome) && Objects.equals(idProposta, that.idProposta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento, nome, idProposta);
	}
}
