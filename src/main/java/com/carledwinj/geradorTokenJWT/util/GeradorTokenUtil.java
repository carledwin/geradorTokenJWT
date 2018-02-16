package com.carledwinj.geradorTokenJWT.util;

import java.util.Date;

import com.carledwinj.geradorTokenJWT.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GeradorTokenUtil {

	public static String geraToken(Usuario usuarioDB) {
		
		 String token = Jwts
				.builder()
				.setExpiration(new Date(System.currentTimeMillis() + 3000))
				.setSubject(usuarioDB.getLogin())
				.claim("roles", "user")
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.compact();
		 
		return token;
	}
	
	public static boolean validaToken(String token) {
		
		 /*String token = Jwts
				.builder()
				.setExpiration(new Date(System.currentTimeMillis() + 3000))
				.setSubject(usuarioDB.getLogin())
				.claim("roles", "user")
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.compact();
		 
		return token;*/
		return true
	}
}
