package br.upf.ZoologicoAmigo.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_especie")
@NamedQueries({
	@NamedQuery(name = "EspecieEntity.findByNome", query = "SELECT e FROM EspecieEntity e WHERE e.nome = :nome"),
	@NamedQuery(name = "EspecieEntity.findByComida", query = "SELECT e FROM EspecieEntity e WHERE e.comida = :comida")})
@NamedNativeQueries({
	@NamedNativeQuery(name = "EspecieEntity.findByPartNome",
			query = "SELECT * FROM tb_especie AS especie WHERE especie.nome ~* ?",
			resultClass = EspecieEntity.class)})
public class EspecieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String comida;
	private String habitat;
	
	public EspecieEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComida() {
		return comida;
	}

	public void setComida(String comida) {
		this.comida = comida;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EspecieEntity other = (EspecieEntity) obj;
		return Objects.equals(id, other.id);
	}
	
}
