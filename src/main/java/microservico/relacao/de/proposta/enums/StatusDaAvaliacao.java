package microservico.relacao.de.proposta.enums;

public enum StatusDaAvaliacao {
	
	COM_RESTRICAO(StatusDaProposta.NAO_ELEGIVEL),
	SEM_RESTRICAO(StatusDaProposta.ELEGIVEL);
	
	private StatusDaProposta status;
	
	StatusDaAvaliacao(StatusDaProposta status) {
		this.status = status;
	}

	public StatusDaProposta getStatus() {
		return status;
	}


	
}
