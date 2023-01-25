package com.xprodcda.spring.xprodcda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "produitfab")
public class ProduitFab extends Produit{

	@Column(name = "nbHeuresMoy")
	private int nbHeuresMoy;

	public ProduitFab(Long id, String ref, String designation, String descriptif, float prixUHT, int nbHeuresMoy) {
		super(id, ref, designation, descriptif, prixUHT);
		this.nbHeuresMoy = nbHeuresMoy;
	}
	
	public ProduitFab(Long id, String ref, String designation, String descriptif, float prixUHT) {
		super(id, ref, designation, descriptif, prixUHT);
		
	}
	
	public ProduitFab() {
		super();
		
	}

	public int getNbHeuresMoy() {
		return nbHeuresMoy;
	}

	public void setNbHeuresMoy(int nbHeuresMoy) {
		this.nbHeuresMoy = nbHeuresMoy;
	}
	
	
	
	
}
