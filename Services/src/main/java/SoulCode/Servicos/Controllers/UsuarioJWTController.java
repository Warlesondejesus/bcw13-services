package SoulCode.Servicos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SoulCode.Servicos.Models.UsuarioJWT;
import SoulCode.Servicos.Repositories.UsuarioJWTRepository;
import SoulCode.Servicos.Services.UsuarioJWTService;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class UsuarioJWTController {

	
	@Autowired
	UsuarioJWTService usuarioJWTService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UsuarioJWTRepository usuarioJWTRepository;
	
	@GetMapping("/usuarioJWT")
	public ResponseEntity<List<UsuarioJWT>> listarUsuarioJWT(){
		return ResponseEntity.ok(usuarioJWTService.listarUsuarioJWT());
	}
	
	@PostMapping("/usuarioJWT")
	public ResponseEntity<UsuarioJWT> inserirUsuario(@RequestBody UsuarioJWT usuarioJWT){
		usuarioJWT.setPassword(encoder.encode(usuarioJWT.getPassword()));
		return ResponseEntity.ok().body(usuarioJWTService.inserirUsuario(usuarioJWT));
	}
	
}








