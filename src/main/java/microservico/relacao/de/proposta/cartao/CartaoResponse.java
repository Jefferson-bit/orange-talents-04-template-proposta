package microservico.relacao.de.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartaoResponse {

	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private List<BloqueioResponse> bloqueios = new ArrayList<>();
	private List<AvisoResponse> avisos = new ArrayList<>();
	private List<ParcelaResponse> parcelas = new ArrayList<>();
	private BigDecimal limite;
	private RenegociacaoResponse renegociacao;
	private VencimentoResponse vencimento;
	private Long idProposta;

	public CartaoResponse() {
	}
	
	public List<AvisoResponse> getAvisos() {
		return avisos;
	}

	public List<BloqueioResponse> getBloqueios() {
		return bloqueios;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getId() {
		return id;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public List<ParcelaResponse> getParcelas() {
		return parcelas;
	}

	public RenegociacaoResponse getRenegociacao() {
		return renegociacao;
	}

	public String getTitular() {
		return titular;
	}

	public VencimentoResponse getVencimento() {
		return vencimento;
	}

}
