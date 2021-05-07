package microservico.relacao.de.proposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import microservico.relacao.de.proposta.validacao.CpfOuCnpj;

public class NovaPropostaRequest {

	@Email(message = "Email invalido")
	@NotBlank(message = "Email não pode ser vazio")
	private String email;
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	@NotBlank(message = "Endereço não pode ser vazio")
	private String endereco;
	@NotNull(message = "Salário não pode ser nulo")
	@Positive
	private BigDecimal salario;
	@NotBlank(message = "CPF/CNPJ não pode ser vazio")
	@CpfOuCnpj
	private String cpfOuCnpj;

	public NovaPropostaRequest() {
	}

	public NovaPropostaRequest(
			@Email(message = "Email invalido") @NotBlank(message = "Email não pode ser vazio") String email,
			@NotBlank(message = "Nome não pode ser vazio") String nome,
			@NotBlank(message = "Endereço não pode ser vazio") String endereco,
			@NotNull(message = "Salário não pode ser nulo") @Positive BigDecimal salario,
			@NotBlank(message = "CPF/CNPJ não pode ser vazio") String cpfOuCnpj) {
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Proposta toModel() {
		return new Proposta(email, nome, endereco, salario, cpfOuCnpj);
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
