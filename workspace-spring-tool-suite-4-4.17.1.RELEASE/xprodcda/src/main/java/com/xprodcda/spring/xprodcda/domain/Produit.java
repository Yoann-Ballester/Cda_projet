package com.xprodcda.spring.xprodcda.domain;

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
	@Column(name = "refinterne")	
	private String ref;
	@Column(name = "designation")	
	private String designation;
	@Column(name = "descriptif")	
	private String descriptif;
	@Column(name = "prixUHT")	
	private float prixUHT;
	@Column(name = "img")	
	private String img;
	
	// EXEMPLE ONETOMANY
//	@Column(name = "ListClass")
//	@OneToMany (fetch = FetchType.LAZY, mappedBy = "id")
//	private List<Class> listClass = new ArrayList<>();
	
	public Produit(Long id, String ref, String designation, String descriptif, float prixUHT, String img) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.descriptif = descriptif;
		this.prixUHT = prixUHT;
		this.img = img;
	}

	public Produit(Long id, String ref, String designation, String descriptif, float prixUHT) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.descriptif = descriptif;
		this.prixUHT = prixUHT;
	}
	
	public Produit() {
		super();
		
	}
	
	

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return ref;
	}
	public void setReference(String ref) {
		this.ref = ref;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
