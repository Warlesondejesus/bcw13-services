package SoulCode.Servicos.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import SoulCode.Servicos.Data.DetalheUsuarioData;
import SoulCode.Servicos.Models.UsuarioJWT;

public class JWTAutenticarFilter  extends UsernamePasswordAuthenticationFilter{
	
	public static final int TOKEN_EXPIRACAO = 600_000;
	
	public static final String TOKEN_SENHA = "1f5adfb0920e311240f36e325f44dc73445d4cd8b5540aea9722daffa00745fa774ea2a02f3c860661b68e4c7c3cd44bda2b9cbe82e6f4c4130bc34c3a96d7e5";
	
	private final AuthenticationManager authenticationManager;
	
	public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	// O método attemptAuthentication faz a tentativa de autenticação.
	// Nesse momento é verificado a autenticidade do username e password do usuário.
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException{
		
		try {
			UsuarioJWT usuario = new ObjectMapper()
					.readValue(request.getInputStream(), UsuarioJWT.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					usuario.getLogin(),
					usuario.getPassword(), 
					new ArrayList<>()));
		}catch( IOException e) {
			throw new RuntimeException("Falha ao tentar autenticar o usuario", e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication (HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult) throws IOException{
		DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(usuarioData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));
		
		response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
        response.getWriter().flush();
	}
	
	
	
	
	
	

}
