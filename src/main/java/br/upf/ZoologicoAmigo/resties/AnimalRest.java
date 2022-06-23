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

import br.upf.ZoologicoAmigo.entities.AnimalEntity;
import br.upf.ZoologicoAmigo.jwt.TokenJwt;
import br.upf.ZoologicoAmigo.repositories.AnimalRepository;

@RestController
@RequestMapping(value = "/ZoologicoAmigo/animal")
public class AnimalRest {
	
	@Autowired
	private AnimalRepository repository;
	
	@GetMapping(value = "/findAll")
	public List<AnimalEntity> findAll(@RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<AnimalEntity> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/findById")
	public AnimalEntity findById(@RequestHeader(value = "id") Long id, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		AnimalEntity result = repository.findById(id).get();
		return result;
	}
	
	@GetMapping(value = "/findByNome")
	public List<AnimalEntity> findByNome(@RequestHeader(value = "nome") String nome, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<AnimalEntity> entityList = repository.findByNome(nome);
		return entityList;
	}
	
	@GetMapping(value = "/findByPartNome")
	public List<AnimalEntity> findByPartNome(@RequestHeader(value = "partNome") String partNome, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<AnimalEntity> entityList = repository.findByPartNome(partNome);
		return entityList;
	}
	
	@GetMapping(value = "/findByEspecieNome")
	public List<AnimalEntity> findByEspecieNome(@RequestHeader(value = "especie") String especie, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<AnimalEntity> entityList = repository.findByEspecieNome(especie);
		return entityList;
	}
	
	@PostMapping(value = "/insert")
	public AnimalEntity insert(@RequestBody AnimalEntity animal) {
		AnimalEntity result = repository.save(animal);
		return result;
	}
	
	@PutMapping(value = "/update")
	public AnimalEntity update(@RequestBody AnimalEntity animal) {
		AnimalEntity result = repository.save(animal);
		return result;
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestHeader(value = "id") Long id, @RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		repository.deleteById(id);
	}
	
}
