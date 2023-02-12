package com.ezcomp.spring.ezcomp.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="produit")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produit implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idproduit")	
	private Long id;
	private String ref;
	@Column(name = "descriptif")	
	private String descriptif;
	@Column(name = "prixUHT")	
	private float prixUHT;
	@Column(name = "img")	
	private String img;
	
	
	public Produit(Long id, String ref, String descriptif, float prixUHT, String img) {
		super();
		this.id = id;
		this.ref = ref;
		this.descriptif = descriptif;
		this.prixUHT = prixUHT;
		this.img = img;
	}


	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
	}


	public String getDescriptif() {
		return descriptif;
	}


	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}


	public float getPrixUHT() {
		return prixUHT;
	}


	public void setPrixUHT(float prixUHT) {
		this.prixUHT = prixUHT;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}
	
	
	
	
	// EXEMPLE ONETOMANY
//	@Column(name = "ListClass")
//	@OneToMany (fetch = FetchType.LAZY, mappedBy = "id")
//	private List<Class> listClass = new ArrayList<>();
	
	

	

	
	
}
