package microservico.relacao.de.proposta.avaliacao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import microservico.relacao.de.proposta.enums.StatusDaAvaliacao;
import microservico.relacao.de.proposta.proposta.Proposta;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String documento;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "proposta_id")
	private Proposta proposta;
	@Enumerated(EnumType.STRING)
	private StatusDaAvaliacao statusDaAvaliacao;

	public Avaliacao(String documento, String nome, Proposta proposta, StatusDaAvaliacao statusDaAvaliacao) {
		this.documento = documento;
		this.nome = nome;
		this.proposta = proposta;
		this.statusDaAvaliacao = statusDaAvaliacao;
	}

	public String getDocumento() {
		return documento;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Proposta getProposta() {
		return proposta;
	}
	
	public StatusDaAvaliacao getStatusDaAvaliacao() {
		return statusDaAvaliacao;
	}
	

}
