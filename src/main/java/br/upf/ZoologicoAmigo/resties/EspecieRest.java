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

import br.upf.ZoologicoAmigo.entities.EspecieEntity;
import br.upf.ZoologicoAmigo.jwt.TokenJwt;
import br.upf.ZoologicoAmigo.repositories.EspecieRepository;


@RestController
@RequestMapping(value = "/ZoologicoAmigo/especie")
public class EspecieRest {
	
	@Autowired
	private EspecieRepository repository;
	
	@GetMapping(value = "/findAll")
	public List<EspecieEntity> findAll(@RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<EspecieEntity> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/findById")
	public EspecieEntity findById(@RequestHeader(value = "id") Long id, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		EspecieEntity result = repository.findById(id).get();
		return result;
	}
	
	@GetMapping(value = "/findByNome")
	public List<EspecieEntity> findByNome(@RequestHeader(value = "nome") String nome, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<EspecieEntity> entityList = repository.findByNome(nome);
		return entityList;
	}
	
	@GetMapping(value = "/findByPartNome")
	public List<EspecieEntity> findByPartNome(@RequestHeader(value = "partNome") String partNome, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<EspecieEntity> entityList = repository.findByPartNome(partNome);
		return entityList;
	}
	
	@PostMapping(value = "/insert")
	public EspecieEntity insert(@RequestBody EspecieEntity especie) {
		EspecieEntity result = repository.save(especie);
		return result;
	}
	
	@PutMapping(value = "/update")
	public EspecieEntity update(@RequestBody EspecieEntity especie) {
		EspecieEntity result = repository.save(especie);
		return result;
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestHeader(value = "id") Long id, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		repository.deleteById(id);
	}
}