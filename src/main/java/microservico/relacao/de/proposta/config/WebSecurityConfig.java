package microservico.relacao.de.proposta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests ->
		authorizeRequests
				.antMatchers(HttpMethod.GET, "/api/acompanhaPropostas/**").hasAuthority("SCOPE_proposta-scope")
				.antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_proposta-scope")
				.antMatchers(HttpMethod.GET, "/api/propostas/**").hasAuthority("SCOPE_proposta-scope")
				.antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_proposta-scope")
				.antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_cartoes:read")
				.antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_cartoes:write")
				.anyRequest().authenticated())
		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}	
}
