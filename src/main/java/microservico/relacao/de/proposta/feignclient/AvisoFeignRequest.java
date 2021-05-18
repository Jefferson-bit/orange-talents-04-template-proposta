package microservico.relacao.de.proposta.feignclient;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import microservico.relacao.de.proposta.aviso.Aviso;
import microservico.relacao.de.proposta.cartao.Cartao;

public class AvisoFeignRequest {
	
	@NotBlank(message = "Destino não pode ser nulo ou vazio")
	private String destino;
	@NotNull(message = "Data não pode ser nula")
	@Future
	@JsonFormat(pattern =  "yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate validoAte;
	private String userAgent;
	private String ipDoCliente;
	
	public AvisoFeignRequest() {
	}
	
	public AvisoFeignRequest(@NotBlank(message = "Destino não pode ser nulo ou vazio") String destino,
			@NotNull(message = "Data não pode ser nula") @Future LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public Aviso toModel(Cartao cartao, HttpServletRequest servletRequest) {
		 userAgent = servletRequest.getHeader("User-Agent");
		 ipDoCliente = servletRequest.getRemoteAddr();
		return new Aviso(destino, validoAte, userAgent, ipDoCliente, cartao);
	}
	
	public String getDestino() {
		return destino;
	}
	
	public LocalDate getValidoAte() {
		return validoAte;
	}
}
