package microservico.relacao.de.proposta.excecao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeError extends ErrorPadrao {

	private List<CampoEMessagemError> listError = new ArrayList<>();

	public ValidadorDeError(LocalDateTime timesTamp, Integer status, String error, String message, String path) {
		super(timesTamp, status, error, message, path);
	}

	public void addError(String fieldMessage, String defaultMessage) {
		listError.add(new CampoEMessagemError(fieldMessage, defaultMessage));
	}

	public List<CampoEMessagemError> getListError() {
		return listError;
	}

}
