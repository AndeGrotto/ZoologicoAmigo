package br.upf.ZoologicoAmigo.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.upf.ZoologicoAmigo.entities.AnimalEntity;

@Repository
public abstract class AnimalRepositoryImpl implements AnimalRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AnimalEntity> findByNome(String nome) {
		List<AnimalEntity> entityList = new ArrayList<>();
		
		TypedQuery<AnimalEntity> query = em.createNamedQuery("AnimalEntity.findByNome", 
				AnimalEntity.class);
		query.setParameter("nome", nome);
		entityList = query.getResultList();
		return entityList;
	}
	
	@Override
	public List<AnimalEntity> findByPartNome(String partNome) {
		List<AnimalEntity> entityList = new ArrayList<>();
		
		TypedQuery<AnimalEntity> query = em.createNamedQuery("AnimalEntity.findByPartNome", 
				AnimalEntity.class);
		query.setParameter("partNome", partNome);
		entityList = query.getResultList();
		return entityList;
	}
	
	@Override
	public List<AnimalEntity> findByEspecieNome(String especieNome) {
		List<AnimalEntity> entityList = new ArrayList<>();
		TypedQuery<AnimalEntity> query = em.createNamedQuery("AnimalEntity.findByEspecieNome", 
				AnimalEntity.class);
		query.setParameter("especieId", especieNome);
		entityList = query.getResultList();
		return entityList;
	}
}

