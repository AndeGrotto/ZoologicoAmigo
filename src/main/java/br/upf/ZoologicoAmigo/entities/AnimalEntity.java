package br.upf.ZoologicoAmigo.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_animal")
@NamedQueries({
	@NamedQuery(name = "AnimalEntity.findByNome", query = "SELECT a FROM AnimalEntity a WHERE a.nome = :nome"),
	@NamedQuery(name = "AnimalEntity.findByEspecie", query = "SELECT a FROM AnimalEntity a WHERE a.especie.id = :especieId")})
@NamedNativeQueries({
	@NamedNativeQuery(name = "AnimalEntity.findByPartNome",
			query = "SELECT * FROM tb_animal AS animal WHERE animal.nome ~* ?",
			resultClass = AnimalEntity.class)})
public class AnimalEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Character sexo;
	private Date datanascimento;
	private Double peso;
	private Double altura;
	
	@ManyToOne
	@JoinColumn(name = "especie_id")
	private EspecieEntity especie;

	public AnimalEntity() {

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

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public EspecieEntity getEspecie() {
		return especie;
	}

	public void setEspecie(EspecieEntity especie) {
		this.especie = especie;
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
		AnimalEntity other = (AnimalEntity) obj;
		return Objects.equals(id, other.id);
	}
	
}
