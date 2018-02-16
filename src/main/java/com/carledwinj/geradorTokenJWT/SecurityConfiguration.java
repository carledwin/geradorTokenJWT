package com.carledwinj.geradorTokenJWT;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
			.withUser("carl").password("senha123").roles("USER")
		.and()	
			.withUser("admin").password("senha456").roles("USER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
		.and()
			.authorizeRequests()
				.antMatchers("/usuario/**", "/token/**", "/h2-console/**").hasRole("USER")
				.antMatchers("/**").hasAnyRole("ADMIN")
		.and()
			.csrf()
				.disable()
				.headers()
				.frameOptions().disable();
		
			
			
	}
}
