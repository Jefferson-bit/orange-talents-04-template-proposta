package microservico.relacao.de.proposta.excecao;

public class RecursoNaoEncontradoExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoExcecao(String msg) {
		super(msg);
	}
}
