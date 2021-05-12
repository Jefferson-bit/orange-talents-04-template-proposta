package microservico.relacao.de.proposta.proposta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import microservico.relacao.de.proposta.avaliacao.Avaliacao;
import microservico.relacao.de.proposta.enums.StatusDaProposta;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private BigDecimal salario;
	@Column(nullable = false)
	private String documento;
	@OneToMany(mappedBy = "proposta")
	private List<Avaliacao> avaliacoes = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private StatusDaProposta statusDaProposta;
	private String numeroDoCartao;

	public Proposta() {
	}

	public Proposta(String email, String nome, String endereco, BigDecimal salario, String documento) {
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.documento = documento;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
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

	public StatusDaProposta getStatusDaProposta() {
		return statusDaProposta;
	}

	public void setStatusDaProposta(StatusDaProposta statusDaProposta) {
		this.statusDaProposta = statusDaProposta;
	}

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(String numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

}
