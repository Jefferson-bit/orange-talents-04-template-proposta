package microservico.relacao.de.proposta.cartao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import microservico.relacao.de.proposta.aviso.Aviso;
import microservico.relacao.de.proposta.biometria.Biometria;
import microservico.relacao.de.proposta.bloqueio.Bloqueio;
import microservico.relacao.de.proposta.carteira.Carteira;
import microservico.relacao.de.proposta.proposta.Proposta;
import microservico.relacao.de.proposta.utils.Criptografia;

@Entity
public class Cartao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column( nullable = false)
	@Convert(converter = Criptografia.class)
	private String numero;
	private LocalDateTime emitidoEm;
	@Column( nullable = false)
	private String titular;
	@Column( nullable = false)
	private BigDecimal limite;
	@OneToMany(mappedBy = "cartao")
	private List<Biometria> biometrias = new ArrayList<>();
	@OneToMany(mappedBy = "cartao")
	private List<Bloqueio> bloqueios = new ArrayList<>();
	@OneToMany(mappedBy = "cartao")
	private List<Aviso> avisos = new ArrayList<>();
	@OneToMany(mappedBy = "cartao")
	private Set<Carteira> carteiras = new HashSet<>();
	@OneToOne
	@JoinColumn(name = "proposta_id")
	private Proposta proposta;

	@Deprecated
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
	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}
	
	public List<Aviso> getAvisos() {
		return avisos;
	}
	
	public Set<Carteira> getCarteiras() {
		return carteiras;
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

}
