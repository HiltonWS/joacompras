package com.rhcloud.joacompras.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
/**
 * @author Hilton Wichwski Silva
 *
 */
@Entity
@Table(name="LISTA")
public class ListaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LISTA", nullable = false)
	private Long id;
	
	@Column(name="NM_LISTA", nullable = false)
	private String nome;
	
	/**
	 * @return nome
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaBean other = (ListaBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
