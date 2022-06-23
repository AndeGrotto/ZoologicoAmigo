package br.upf.ZoologicoAmigo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.upf.ZoologicoAmigo.entities.EspecieEntity;


public interface EspecieRepository extends JpaRepository<EspecieEntity, Long> {

	List<EspecieEntity> findByNome(String nome);
	
	List<EspecieEntity> findByPartNome(String partNome);
}