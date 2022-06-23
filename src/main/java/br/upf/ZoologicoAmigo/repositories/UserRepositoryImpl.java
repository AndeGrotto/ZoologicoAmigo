package br.upf.ZoologicoAmigo.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.upf.ZoologicoAmigo.entities.UserEntity;

@Repository
public abstract class UserRepositoryImpl implements UserRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserEntity findByUsuario(String usuario) {
		UserEntity entity = new UserEntity();
		
		TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findByUsuario", 
				UserEntity.class);
		query.setParameter("usuario", usuario);
		query.setMaxResults(1);
		entity = query.getSingleResult();
		
		return entity;
	}
}
