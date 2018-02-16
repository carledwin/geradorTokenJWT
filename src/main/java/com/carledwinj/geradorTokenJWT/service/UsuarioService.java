package com.carledwinj.geradorTokenJWT.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carledwinj.geradorTokenJWT.model.Usuario;
import com.carledwinj.geradorTokenJWT.repository.UsuarioRepository;
import com.carledwinj.geradorTokenJWT.util.GeneratorPasswordUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService {

	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public String geraTokenUsuario(Usuario usuario) {
		
		if(isUserValido(usuario)) {
			
			Usuario usuarioDB = consultaUsuarioBaseDados(usuario);
			
			return geraToken(usuarioDB);
		}
		
		throw new RuntimeException("Dados inválidos"); 
	}

	private String geraToken(Usuario usuarioDB) {
		
		 String token = Jwts
				.builder()
				.setSubject(usuarioDB.getLogin())
				.claim("roles", "user")
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.compact();
		 
		return token;
	}

	private boolean isUserValido(Usuario usuario) {
		return usuario != null && StringUtils.isNotBlank(usuario.getLogin()) && StringUtils.isNotBlank(usuario.getPassword());
	}
	
	private Usuario consultaUsuarioBaseDados(Usuario usuario) {
		
		usuario.setPassword(GeneratorPasswordUtil.generateHash(usuario.getPassword()));
		
		Usuario usuarioDB = usuarioRepository.findByLoginAndPassword(usuario.getLogin(), usuario.getPassword());

		if (usuarioDB == null) {
			throw new RuntimeException("Usuário não encontrado na base de dados");
		}

		return usuarioDB;
	}

	public Usuario cadastraUsuario(Usuario usuario) {
		
		usuario.setPassword(GeneratorPasswordUtil.generateHash(usuario.getPassword()));
		
		return usuarioRepository.save(usuario);
	}

}
