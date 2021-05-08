package microservico.relacao.de.proposta.validacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import microservico.relacao.de.proposta.proposta.NovaPropostaRequest;
import microservico.relacao.de.proposta.proposta.PropostaRepository;

@Component
public class CampoUnico implements Validator{
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovaPropostaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovaPropostaRequest request = (NovaPropostaRequest) target;
		Optional<String> cpfOuCnpjOptional = propostaRepository.findBycpfOuCnpj(request.getCpfOuCnpj());
		if(cpfOuCnpjOptional.isPresent()) {
			 errors.rejectValue("cpfOuCnpj", null, "Já existe CPF/CNPJ " + request.getCpfOuCnpj());
		}
	}
}
