package microservico.relacao.de.proposta.cartao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import microservico.relacao.de.proposta.biometria.Biometria;
import microservico.relacao.de.proposta.bloqueio.Bloqueio;
import microservico.relacao.de.proposta.proposta.Proposta;

@Entity
public class Cartao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	@OneToMany(mappedBy = "cartao")
	private List<Biometria> biometrias = new ArrayList<>();
	@OneToMany(mappedBy = "cartao")
	private List<Bloqueio> bloqueios = new ArrayList<>();
	@OneToOne
	@JoinColumn(name = "proposta_id")
	private Proposta proposta;

	public Cartao() {
	}

	public Cartao(String numero, LocalDateTime emitidoEm, String titular, BigDecimal limite, Proposta proposta) {
		this.numero = numero;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	public Cartao(String numero, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
		this.numero = numero;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
	}

	@PrePersist
	public void createdAt() {
		emitidoEm = LocalDateTime.now();
	}

	public List<Biometria> getBiometrias() {
		return biometrias;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public String getTitular() {
		return titular;
	}

	public Long getId() {
		return id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}
	
}
