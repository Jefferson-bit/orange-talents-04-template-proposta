package microservico.relacao.de.proposta.enums;

public enum StatusDaAvaliacao {
	
	COM_RESTRICAO {
		@Override
		public StatusDaProposta statusDaSolicitacao() {
			return StatusDaProposta.NAO_ELEGIVEL;
		}
	},
	SEM_RESTRICAO {
		@Override
		public StatusDaProposta statusDaSolicitacao() {
			return StatusDaProposta.ELEGIVEL;
		}
	};
	
	public abstract StatusDaProposta statusDaSolicitacao();
}
