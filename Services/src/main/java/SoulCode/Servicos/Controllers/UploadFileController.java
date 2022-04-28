package SoulCode.Servicos.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import SoulCode.Servicos.Services.FuncionarioService;
import SoulCode.Servicos.Utils.UploadFile;

@CrossOrigin
@RequestMapping("servicos")
@RestController
public class UploadFileController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@PostMapping("/funcionario/envioFoto/{idFuncionario}")
<<<<<<< HEAD
	public ResponseEntity<Void> enviarDados(@PathVariable Integer idFuncionario,
			MultipartFile foto, @RequestParam("nome") String nome){
		String fileName = nome;
		String uploadDir = "/home/tatiana/cursoJava/frontEscola/src/assets/imagens";
		String nomeMaisCaminho = "assets/imagens/" + nome;
=======
	public ResponseEntity<Void> enviarDados(@PathVariable Integer idFuncionario, MultipartFile foto, @RequestParam("nome") String nome) {
		String fileName = nome;
		String uploadDir = "/home/renato3x/Documents/apps/bcw13-services/frontend/src/assets/funcionarios";
		String nomeMaisCaminho = "/assets/funcionarios/" + nome;
>>>>>>> a52e7bda694f92c3d1ee2fe94bd84eac4c611389
		
		funcionarioService.salvarFoto(idFuncionario, nomeMaisCaminho);
		
		try {
			UploadFile.salvarArquivo(uploadDir, fileName, foto);
<<<<<<< HEAD
		} catch (Exception e){
			System.out.println("O arquivo não foi enviado: " + e);
		}
		System.out.println("Arquivo enviado: " + nomeMaisCaminho);
		return ResponseEntity.ok().build();
	} 

=======
		} catch (Exception e) {
			System.out.println("O arquivo não foi enviado: " + e);
		}

		System.out.println("Arquivo enviado: " + nomeMaisCaminho);
		return ResponseEntity.ok().build();
	}
>>>>>>> a52e7bda694f92c3d1ee2fe94bd84eac4c611389
}











