package SoulCode.Servicos.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoulCode.Servicos.Models.UsuarioJWT;
import SoulCode.Servicos.Repositories.UsuarioJWTRepository;

@Service
public class UsuarioJWTService {
	
	@Autowired
	UsuarioJWTRepository usuarioJWTRepository;
	
	public List<UsuarioJWT> listarUsuarioJWT(){
		return usuarioJWTRepository.findAll();
	}
	
	public UsuarioJWT inserirUsuario(UsuarioJWT usuarioJWT) {
		return usuarioJWTRepository.save(usuarioJWT);
	}

}
