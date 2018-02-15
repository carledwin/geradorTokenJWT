package com.carledwinj.geradorTokenJWT.restcontroller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carledwinj.geradorTokenJWT.model.Erro;
import com.carledwinj.geradorTokenJWT.model.Usuario;
import com.carledwinj.geradorTokenJWT.service.UsuarioService;

@RestController
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;
	

	@PostMapping(value="/usuario")
	public ResponseEntity<?> getToken(@RequestBody Usuario usuario) {
		
		if(usuario != null && StringUtils.isNotBlank(usuario.getLogin()) && StringUtils.isNotBlank(usuario.getPassword())) {
			
			Usuario usuarioDB = usuarioService.cadastraUsuario(usuario);
			
			if(usuarioDB != null) {
				return new ResponseEntity<String>(usuario.toString(), HttpStatus.CREATED);
			}
			
		}else {
			return new ResponseEntity<Erro>(new Erro("Login e Password são de preenchimento obrigatório."), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Erro>(new Erro("Falha ao tentar criar usuário."), HttpStatus.BAD_REQUEST);
	}
}
