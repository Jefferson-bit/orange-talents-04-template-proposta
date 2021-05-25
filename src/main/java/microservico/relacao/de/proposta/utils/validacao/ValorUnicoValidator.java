package microservico.relacao.de.proposta.utils.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

	private String campoPesquisado;
	private Class<?> tabela;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ValorUnico params) {
		this.campoPesquisado = params.campo();
		this.tabela = params.tabela();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Boolean resultadoUnico = manager.createQuery("select count(t) < 1 from " + tabela.getName() + " t where "
				+ campoPesquisado + "= :value", Boolean.class)
		.setParameter("value", value)
		.getSingleResult();
		return resultadoUnico;
	}

}
