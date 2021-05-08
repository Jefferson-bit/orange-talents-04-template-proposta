package microservico.relacao.de.proposta.enums;

public enum TipoDaSolicitacao {
	
	COM_RESTRICAO {
		@Override
		public StatusDaSolicitacao statusDaSolicitacao() {
			return StatusDaSolicitacao.NAO_ELEGIVEL;
		}
	},
	SEM_RESTRICAO {
		@Override
		public StatusDaSolicitacao statusDaSolicitacao() {
			return StatusDaSolicitacao.ELEGIVEL;
		}
	};
	
	public abstract StatusDaSolicitacao statusDaSolicitacao();
}
