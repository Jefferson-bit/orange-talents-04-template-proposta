package microservico.relacao.de.proposta.excecao;

public class CampoEMessagemError {

	private String fieldMessage;
	private String defaultMessage;

	public CampoEMessagemError(String fieldMessage, String defaultMessage) {
		this.fieldMessage = fieldMessage;
		this.defaultMessage = defaultMessage;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}
}
