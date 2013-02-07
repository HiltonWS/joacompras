package com.rhcloud.joacompras.core.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.rhcloud.joacompras.core.dao.ItemDAO;
@Entity
@Table(name="ITEM")
public class ItemBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ITEM", nullable = false)
	private Long id;
	
    public void setId(Long id) {
		this.id = id;
	}
	@Column(name="NM_ITEM", nullable = false) 
    private String nome;
    
    @Column(name="VL_ITEM", nullable = false)
    private Double valor;
    
    @Column(name="IMAGEM", columnDefinition="mediumblob", updatable= false)
    private byte[] imagem;
    
	@Transient
	private Integer tempQtd;
   

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTempQtd() {
		return tempQtd;
	}

	public void setTempQtd(Integer tempQtd) {
		this.tempQtd = tempQtd;
	}

	public byte[] getImagem() {
		
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	 
	public boolean equals(Object o){
	    if (o instanceof ItemBean){
	        ItemBean temp = (ItemBean)o;
	        if (this.id.equals(temp.getId()))
	            return true;
	    }
	    return false;
	}

} 
