package br.upf.ZoologicoAmigo.resties;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upf.ZoologicoAmigo.entities.UserEntity;
import br.upf.ZoologicoAmigo.jwt.TokenJwt;
import br.upf.ZoologicoAmigo.repositories.UserRepository;
import br.upf.ZoologicoAmigo.utils.Utils;

@RestController
@RequestMapping(value = "/ZoologicoAmigo/user")
public class UserRest {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping(value = "/findAll")
	public List<UserEntity> findAll(@RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<UserEntity> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/findById")
	public UserEntity findById(@RequestHeader(value = "id") Long id, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		UserEntity result = repository.findById(id).get();
		return result;
	}
	
	@GetMapping(value = "/findByUser")
	public UserEntity findByUsuario(@RequestHeader(value = "usuario") String usuario, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		UserEntity result = repository.findByUsuario(usuario);
		return result;
	}
	
	@PostMapping(value = "/insert")
	public UserEntity insert(@RequestBody UserEntity user) {
		UserEntity result = repository.save(user);
		return result;
	}
	
	@PutMapping(value = "/update")
	public UserEntity update(@RequestBody UserEntity user) {
		UserEntity result = repository.save(user);
		return result;
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestHeader(value = "id") Long id, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		repository.deleteById(id);
	}
	
	@GetMapping(value = "/autorizar")
	public UserEntity autorizar(@RequestHeader("usuario") String usuario, @RequestHeader("senha") String senha) {
		
		UserEntity entity;
		
		if(usuario != null && !usuario.isEmpty() && senha != null && !senha.isEmpty()) {
			entity = repository.findByUsuario(usuario);
			if(entity.getId() != null) {
				if(entity.getSenha().equals(senha)) {
					entity.setToken(Utils.processarTokenJWT(usuario));
					return entity;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
