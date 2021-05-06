package microservico.relacao.de.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String cpfOuCnpj;

	public Proposta() {
	}

	public Proposta(String email, String nome, String endereco, BigDecimal salario, String cpfOuCnpj) {
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Long getId() {
		return id;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
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
}
