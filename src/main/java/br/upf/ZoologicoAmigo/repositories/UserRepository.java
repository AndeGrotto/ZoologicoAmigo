package br.upf.ZoologicoAmigo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.upf.ZoologicoAmigo.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUsuario(String usuario);
	
}
