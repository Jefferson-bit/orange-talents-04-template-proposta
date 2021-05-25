package microservico.relacao.de.proposta.proposta;

import java.math.BigDecimal;

import microservico.relacao.de.proposta.enums.StatusDaProposta;

public class PropostaResponse {

	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private String documento;
	private StatusDaProposta resultadoSolicitacao;
	
	public PropostaResponse(Proposta proposta) {
		email = proposta.getEmail();
		nome = proposta.getNome();
		endereco = proposta.getEndereco();
		salario = proposta.getSalario();
		documento = proposta.getDocumento();
		resultadoSolicitacao = proposta.getResultadoSolicitacao();
	}

	public String getDocumento() {
		return this.documento;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusDaProposta getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}
