package SoulCode.Servicos.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import SoulCode.Servicos.Models.UsuarioJWT;

public class DetalheUsuarioData  implements UserDetails{

	private final Optional<UsuarioJWT> usuario;
	
	public DetalheUsuarioData(Optional<UsuarioJWT> usuario) {
		this.usuario = usuario;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return usuario.orElse(new UsuarioJWT()).getPassword();
	}	

	@Override
	public String getUsername() {
		return usuario.orElse(new UsuarioJWT()).getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
