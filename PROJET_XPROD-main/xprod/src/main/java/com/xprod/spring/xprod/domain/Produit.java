package com.xprod.spring.xprod.domain;

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
@Table(name="PRODUIT")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produit implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUIT_ID")
	private Long idProduit;
	
	@Column(name = "REFINTERNE")
	private String refInterneProduit;
	@Column(name = "DESIGNATION")
	private String designationProduit;
	@Column(name = "DESCRIPTIF")
	private String descriptifProduit;
	@Column(name = "PRIXVENTEHT")
	private double prixVenteUProduit;
	@Column(name = "IMAGE")
	private String imgProduit;
	
	public Long getIdProduit() {
		return idProduit;
	}
	
	
	public String getRefInterneProduit() {
		return refInterneProduit; 
	}
	public String getDesignationProduit() {
		return designationProduit;
	}
	public String getDescriptifProduit() {
		return descriptifProduit;
	}
	public double getPrixVenteUProduit() {
		return prixVenteUProduit;
	}
	public String getImgProduit() {
		return imgProduit;
	}

	
	
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}
	public void setRefInterneProduit(String refInterneProduit) {
		this.refInterneProduit = refInterneProduit;
	}
	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}
	public void setDescriptifProduit(String descriptifProduit) {
		this.descriptifProduit = descriptifProduit;
	}
	public void setPrixVenteUProduit(double prixVenteUProduit) {
		this.prixVenteUProduit = prixVenteUProduit;
	}
	public void setImgProduit(String imgProduit) {
		this.imgProduit = imgProduit;
	}
	
	
	
	
	


	public Produit(Long idProduit, String refInterneProduit, String designationProduit, String descriptifProduit,
			double prixVenteUProduit, String imgProduit) {
		super();
		this.idProduit = idProduit;
		this.refInterneProduit = refInterneProduit;
		this.designationProduit = designationProduit;
		this.descriptifProduit = descriptifProduit;
		this.prixVenteUProduit = prixVenteUProduit;
		this.imgProduit = imgProduit;
	}


	public Produit() {
		super();
	}


	

	
	

	
	
	

}
