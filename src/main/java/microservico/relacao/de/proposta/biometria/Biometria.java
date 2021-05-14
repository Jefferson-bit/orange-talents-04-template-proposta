package microservico.relacao.de.proposta.biometria;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import microservico.relacao.de.proposta.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob	
	private String fingerPrint;
	@ManyToOne
	@JoinColumn( name = "cartao_id")
	private Cartao cartao;
	private Instant dataDaCriacao;

	public Biometria() {
	}
	
	public Biometria(String fingerPrint, Cartao cartao) {
		this.fingerPrint = fingerPrint;
		this.cartao = cartao;
	}

	@PrePersist
	public void createdAt() {
		dataDaCriacao = Instant.now();
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Instant getDataDaCriacao() {
		return dataDaCriacao;
	}

	public String getFingerPrint() {
		return fingerPrint;
	}

	public Long getId() {
		return id;
	}
}
