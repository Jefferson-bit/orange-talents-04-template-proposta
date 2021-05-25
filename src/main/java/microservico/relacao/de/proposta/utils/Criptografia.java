package microservico.relacao.de.proposta.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
import org.springframework.beans.factory.annotation.Value;

@Convert
public class Criptografia implements AttributeConverter<String, String>{
		
	@Value("${chave.algoritmo}")
	private String algoritmo;
	
	@Value("${chave.password}")
	private String password;
	
	@Override
	public String convertToDatabaseColumn(String textoParaEncriptar) {
		StandardPBEStringEncryptor criptografia = new StandardPBEStringEncryptor();
		criptografia.setAlgorithm(algoritmo);
		criptografia.setSaltGenerator(new ZeroSaltGenerator());
		criptografia.setPassword(password);
		return criptografia.encrypt(textoParaEncriptar);
	}

	@Override
	public String convertToEntityAttribute(String textoParaDecriptar) {
		StandardPBEStringEncryptor criptografia = new StandardPBEStringEncryptor();
		criptografia.setAlgorithm(algoritmo);
		criptografia.setSaltGenerator(new ZeroSaltGenerator());
		criptografia.setPassword(password);
		return criptografia.decrypt(textoParaDecriptar);
		
	}
}
