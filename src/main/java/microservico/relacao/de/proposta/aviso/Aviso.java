package microservico.relacao.de.proposta.aviso;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import microservico.relacao.de.proposta.cartao.Cartao;

@Entity
public class Aviso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String destino;
	@Column(nullable = false)
	private LocalDate validoAte;
	@Column(name = "criadoEm")
	private Instant createdAt;
	@Column(name = "userAgent")
	private String sistemaResponsavel;
	private String ipDoCliente;
	@ManyToOne
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;

	@Deprecated
	public Aviso() {
	}

	public Aviso(String destino, LocalDate validoAte, String sistemaResponsavel, String ipDoCliente, Cartao cartao) {
		this.destino = destino;
		this.validoAte = validoAte;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ipDoCliente = ipDoCliente;
		this.cartao = cartao;
	}

	@PrePersist
	public void createdAt() {
		createdAt = Instant.now();
	}

	public String getDestino() {
		return destino;
	}

	public Long getId() {
		return id;
	}

	public String getIpDoCliente() {
		return ipDoCliente;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}
}
