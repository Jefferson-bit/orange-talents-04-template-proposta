package microservico.relacao.de.proposta.feignclient.cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.carteira.Carteira;
import microservico.relacao.de.proposta.enums.StatusDaCarteira;
import microservico.relacao.de.proposta.validacao.ValorUnico;

public class CarteiraFeignRequest {

	@NotBlank(message = "Email não pode ser nulo ou vazio")
	@Email(message = "Email invalido")
	private String email;
	@NotBlank(message = "Carteira não pode ser nulo ou vazio")
	@ValorUnico(tabela = Carteira.class, campo = "emissor", message = "Emissor já existe")
	private String carteira;

	public CarteiraFeignRequest() {
	}

	public CarteiraFeignRequest(
			@NotBlank(message = "Email não pode ser nulo ou vazio") @Email(message = "Email invalido") String email,
			@NotBlank(message = "Carteira não pode ser nulo ou vazio") String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public Carteira toModel(Cartao cartao, StatusDaCarteira status, String id) {
		return new Carteira(email, carteira, cartao, status, id);
	}

	public String getCarteira() {
		return carteira;
	}

	public String getEmail() {
		return email;
	}

}
