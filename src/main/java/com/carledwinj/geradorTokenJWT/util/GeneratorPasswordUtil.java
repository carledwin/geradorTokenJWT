package com.carledwinj.geradorTokenJWT.util;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class GeneratorPasswordUtil {

	private static Object salt;
	
	public static String generateHash(String password) {
		return getInstanceMessageDigesterPassword().encodePassword(password, salt);
	}

	private static MessageDigestPasswordEncoder getInstanceMessageDigesterPassword() {
		return new MessageDigestPasswordEncoder("MD5");
	}
	
	public static boolean isPasswordValid(String password, String hashPassword) {
		return getInstanceMessageDigesterPassword().isPasswordValid(hashPassword, password, salt);
	}
}
