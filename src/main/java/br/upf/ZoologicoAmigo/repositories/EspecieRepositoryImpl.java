package br.upf.ZoologicoAmigo.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.upf.ZoologicoAmigo.entities.EspecieEntity;

@Repository
public abstract class EspecieRepositoryImpl implements EspecieRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<EspecieEntity> findByNome(String nome) {
		List<EspecieEntity> entityList = new ArrayList<>();
		
		TypedQuery<EspecieEntity> query = em.createNamedQuery("EspecieEntity.findByNome", 
				EspecieEntity.class);
		query.setParameter("nome", nome);
		entityList = query.getResultList();
		
		return entityList;
	}
	
	@Override
	public List<EspecieEntity> findByPartNome(String partNome) {
		List<EspecieEntity> entityList = new ArrayList<>();
		TypedQuery<EspecieEntity> query = em.createNamedQuery("EspecieEntity.findByPartNome", 
				EspecieEntity.class);
		query.setParameter("partNome", partNome);
		entityList = query.getResultList();
		return entityList;
	}
}