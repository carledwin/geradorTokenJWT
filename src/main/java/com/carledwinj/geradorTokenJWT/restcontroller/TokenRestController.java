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
public class TokenRestController {

	@Autowired
	private UsuarioService usuarioService;
	

	@PostMapping(value="/token")
	public ResponseEntity<?> getToken(@RequestBody Usuario usuario) {
		
		String token = null;
		
		if(usuario != null && StringUtils.isNotBlank(usuario.getLogin()) && StringUtils.isNotBlank(usuario.getPassword())) {
			
			if(StringUtils.isNotBlank(usuarioService.geraTokenUsuario(usuario))) {
				return new ResponseEntity<String>("Token >>>  [Bearer " + token +"]", HttpStatus.CREATED);
			}
			
		}else {
			return new ResponseEntity<Erro>(new Erro("User e Password são de preenchimento obrigatório."), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Erro>(new Erro("Falha ao tentar gerar token."), HttpStatus.BAD_REQUEST);
	}
}
