package microservico.relacao.de.proposta.bloqueio;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import microservico.relacao.de.proposta.cartao.Cartao;
import microservico.relacao.de.proposta.enums.StatusDoBloqueio;

@Entity
public class Bloqueio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant bloqueadoEm;
	@Column(nullable = false)
	private String sistemaResponsavel;
	private String ipDoCliente;
	@Enumerated(EnumType.STRING)
	private StatusDoBloqueio resultado;
	@ManyToOne
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(String sistemaResponsavel, StatusDoBloqueio resultado, Cartao cartao, String ipDoCliente) {
		this.sistemaResponsavel = sistemaResponsavel;
		this.resultado = resultado;
		this.cartao = cartao;
		this.ipDoCliente = ipDoCliente;
	}
	
	@PrePersist
	public void createdAt() {
		bloqueadoEm = Instant.now();
	}
	
	public Instant getBloqueadoEm() {
		return bloqueadoEm;
	}

	public Long getId() {
		return id;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public StatusDoBloqueio getResultado() {
		return resultado;
	}
	
	public Cartao getCartao() {
		return cartao;
	}
	
	public String getIpDoCliente() {
		return ipDoCliente;
	}	
}
