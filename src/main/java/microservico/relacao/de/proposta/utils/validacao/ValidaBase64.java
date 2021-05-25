package microservico.relacao.de.proposta.utils.validacao;

public class ValidaBase64 {

	public static boolean isValidBase64(String base64) {
		String regex = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$";
		return base64.matches(regex);
	}
}
