package SoulCode.Servicos.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
@Autowired
private ImplementsUserDetailsService userDetailsService;
	
private static final String[] PUBLIC_ENDPOINTS_ADMIN = {
	            "/servicos**/**",
	           "/servicos/orcamento**/**"
};

private static final String[] PUBLIC_ENDPOINTS_USER = {
	            "/servicos/funcionario**/**",
	            "/servicos/servico**/**"
};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		//.antMatchers(HttpMethod.GET, "/").permitAll()
		//.antMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_USER).hasAnyRole("USER","ADMIN")
		//.antMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_ADMIN).hasRole("ADMIN")
       
		.antMatchers(HttpMethod.GET, "/servicos**/**").permitAll()
		.antMatchers(HttpMethod.POST, "/servicos**/**").permitAll()
		
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**", "/style/**");
	}

}
