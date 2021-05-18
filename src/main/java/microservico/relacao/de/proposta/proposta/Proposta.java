package microservico.relacao.de.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import microservico.relacao.de.proposta.cartao.Cartao;
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
	@Enumerated(EnumType.STRING)
	private StatusDaProposta resultadoSolicitacao;
	@OneToOne
	@JoinColumn(name = "cartao_numero", referencedColumnName = "numero")
	private Cartao cartao;

	@Deprecated
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

	public StatusDaProposta getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public void setResultadoSolicitacao(StatusDaProposta resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	
	public Cartao getCartao() {
		return cartao;
	}
	
	public void setCartao(Cartao cartao) {
		if(cartao == null) {
			throw new IllegalArgumentException("Cartão não pode ser nulo");
		}
		this.cartao = cartao;
	}
}