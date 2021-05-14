package microservico.relacao.de.proposta.biometria;

import javax.validation.constraints.NotBlank;

import microservico.relacao.de.proposta.cartao.Cartao;

public class BiometriaRequest {
	
	@NotBlank(message = "Impressão Digital não pode ser nula ou vazia")
	private String fingerPrint;
	
	public BiometriaRequest() {
	}
	
	public Biometria toModel (Cartao cartao) {
		return new Biometria(fingerPrint, cartao);
	}
	
	public String getFingerPrint() {
		return fingerPrint;
	}
	
}
