package com.xprodcda.spring.xprodcda.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produitapp")
public class ProduitApp extends Produit {

	@Column(name = "prixAchatUHT")
	private double prixAchatUHT;
	@Column(name = "refExterne")
	private String refExterne;
	@Column(name = "prixAppUHT")
	private double prixAppUHT;
	
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "produitApps")
	private List<Fournisseur> fournisseurs= new ArrayList<>();
	
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
	private List<LigneDeCommande> ligneDeCommandes = new ArrayList<>();
	
	

	public ProduitApp(Long id, String ref, String designation, String descriptif, float prixUHT, double prixAchatUHT,
			String refExterne, double prixAppUHT, List<Fournisseur> fournisseurs,
			List<LigneDeCommande> ligneDeCommandes) {
		super(id, ref, designation, descriptif, prixUHT);
		this.prixAchatUHT = prixAchatUHT;
		this.refExterne = refExterne;
		this.prixAppUHT = prixAppUHT;
		this.fournisseurs = fournisseurs;
		this.ligneDeCommandes = ligneDeCommandes;
	}

	public ProduitApp(Long id, String ref, String designation, String descriptif, float prixUHT, double prixAchatUHT,
			String refExterne, double prixAppUHT, List<Fournisseur> fournisseurs) {
		super(id, ref, designation, descriptif, prixUHT);
		this.prixAchatUHT = prixAchatUHT;
		this.refExterne = refExterne;
		this.prixAppUHT = prixAppUHT;
		this.fournisseurs = fournisseurs;
	}

	public ProduitApp(Long id, String ref, String designation, String descriptif, float prixUHT, double prixAchatUHT,
			String refExterne, double prixAppUHT) {
		super(id, ref, designation, descriptif, prixUHT);
		this.prixAchatUHT = prixAchatUHT;
		this.refExterne = refExterne;
		this.prixAppUHT = prixAppUHT;
	}

	public ProduitApp(Long id, String ref, String designation, String descriptif, float prixUHT, double prixAchatUHT) {
		super(id, ref, designation, descriptif, prixUHT);
		this.prixAchatUHT = prixAchatUHT;
	}

	public ProduitApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProduitApp(Long id, String ref, String designation, String descriptif, float prixUHT) {
		super(id, ref, designation, descriptif, prixUHT);
		// TODO Auto-generated constructor stub
	}

	public double getPrixAchatUHT() {
		return prixAchatUHT;
	}

	public void setPrixAchatUHT(double prixAchatUHT) {
		this.prixAchatUHT = prixAchatUHT;
	}

	public String getRefExterne() {
		return refExterne;
	}

	public void setRefExterne(String refExterne) {
		this.refExterne = refExterne;
	}

	public double getPrixAppUHT() {
		return prixAppUHT;
	}

	public void setPrixAppUHT(double prixAppUHT) {
		this.prixAppUHT = prixAppUHT;
	}

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

	public List<LigneDeCommande> getLigneDeCommandes() {
		return ligneDeCommandes;
	}

	public void setLigneDeCommandes(List<LigneDeCommande> ligneDeCommandes) {
		this.ligneDeCommandes = ligneDeCommandes;
	}
	
	
	
	
	
}
