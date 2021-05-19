package microservico.relacao.de.proposta.carteira;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.enums.StatusDaCarteira;

@Entity
public class Carteira {

	@Id
	private String id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String emissor;
	@ManyToOne
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusDaCarteira statusDaCarteira;
	private LocalDateTime associadoEm;

	@Deprecated
	public Carteira() {
	}

	public Carteira( String email, String emissor, Cartao cartao, StatusDaCarteira statusDaCarteira, String id) {
		this.email = email;
		this.emissor = emissor;
		this.cartao = cartao;
		this.statusDaCarteira = statusDaCarteira;
		this.id = id;
	}

	@PrePersist
	public void createdAt() {
		associadoEm = LocalDateTime.now();
	}

	public LocalDateTime getAssociadoEm() {
		return associadoEm;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getEmail() {
		return email;
	}

	public String getEmissor() {
		return emissor;
	}
	
	public String getId() {
		return id;
	}

	public StatusDaCarteira getStatusDaCarteira() {
		return statusDaCarteira;
	}
}
