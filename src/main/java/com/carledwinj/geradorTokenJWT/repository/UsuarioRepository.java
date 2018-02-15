package com.carledwinj.geradorTokenJWT.repository;

import org.springframework.data.repository.CrudRepository;

import com.carledwinj.geradorTokenJWT.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByLoginAndPassword(String login, String password);
}
