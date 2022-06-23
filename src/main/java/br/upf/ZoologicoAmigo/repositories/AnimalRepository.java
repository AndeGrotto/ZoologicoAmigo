package br.upf.ZoologicoAmigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upf.ZoologicoAmigo.entities.AnimalEntity;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
	
	List<AnimalEntity> findByNome(String nome);
	
	List<AnimalEntity> findByPartNome(String partNome);
	
	List<AnimalEntity> findByEspecieNome(String nome);
	
}
