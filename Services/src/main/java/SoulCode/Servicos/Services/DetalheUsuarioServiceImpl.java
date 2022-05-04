package SoulCode.Servicos.Services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import SoulCode.Servicos.Data.DetalheUsuarioData;
import SoulCode.Servicos.Models.UsuarioJWT;
import SoulCode.Servicos.Repositories.UsuarioJWTRepository;

public class DetalheUsuarioServiceImpl implements UserDetailsService{
	
	private final UsuarioJWTRepository usuarioJWTRepository;
	
	public DetalheUsuarioServiceImpl(UsuarioJWTRepository usuarioJWTRepository) {
		this.usuarioJWTRepository = usuarioJWTRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioJWT> usuario = usuarioJWTRepository.findByLogin(username);
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não cadastrado");
		}
		return new DetalheUsuarioData(usuario);
	}

}







