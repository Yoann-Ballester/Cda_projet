package com.xprodcda.spring.xprodcda.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSpinner.ListEditor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fournisseur")
public class Fournisseur implements Serializable {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idfour")
	private Long id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "raisonSociale")
	private String raisonSociale;
	@Column(name = "rue")
	private String rue;
	@Column(name = "ville")
	private String ville;
	@Column(name = "cp")
	private String cp;
	@Column(name = "pays")
	private String pays;
	@Column(name = "tel")
	private String tel;
	@Column(name = "email")
	private String email;
	@Column(name = "refExterne")
	private String refExterne;
	@Column(name = "prixAppUHT")
	private double prixAppUHT;
	
	
	@OneToMany (mappedBy =  "id", fetch = FetchType.LAZY)
	private List<Commande> commandes = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "approvisionner", joinColumns = @JoinColumn(name = "idfour"),
	inverseJoinColumns = @JoinColumn(name = "idproduit"))
	private List<ProduitApp> produitApps= new ArrayList<>();
	
	
	

	public Fournisseur(Long id, String nom, String raisonSociale, String rue, String ville, String cp, String pays,
			String tel, String email, String refExterne, double prixAppUHT, List<Commande> commandes,
			List<ProduitApp> produitApps) {
		super();
		this.id = id;
		this.nom = nom;
		this.raisonSociale = raisonSociale;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
		this.pays = pays;
		this.tel = tel;
		this.email = email;
		this.refExterne = refExterne;
		this.prixAppUHT = prixAppUHT;
		this.commandes = commandes;
		this.produitApps = produitApps;
	}

	public Fournisseur(Long id, String nom, String raisonSociale, String rue, String ville, String cp, String pays,
			String tel, String email, String refExterne, double prixAppUHT, List<Commande> commandes) {
		super();
		this.id = id;
		this.nom = nom;
		this.raisonSociale = raisonSociale;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
		this.pays = pays;
		this.tel = tel;
		this.email = email;
		this.refExterne = refExterne;
		this.prixAppUHT = prixAppUHT;
		this.commandes = commandes;
	}

	public Fournisseur(Long id, String nom, String raisonSociale, String rue, String ville, String cp, String pays,
			String tel, String email, String refExterne, double prixAppUHT) {
		super();
		this.id = id;
		this.nom = nom;
		this.raisonSociale = raisonSociale;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
		this.pays = pays;
		this.tel = tel;
		this.email = email;
		this.refExterne = refExterne;
		this.prixAppUHT = prixAppUHT;
	}

	public Fournisseur(Long id, String nom, String raisonSociale, String rue, String ville, String cp, String pays,
			String tel, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.raisonSociale = raisonSociale;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
		this.pays = pays;
		this.tel = tel;
		this.email = email;
	}
	
	public Fournisseur() {
		super();
		
	}
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<ProduitApp> getProduitApps() {
		return produitApps;
	}

	public void setProduitApps(List<ProduitApp> produitApps) {
		this.produitApps = produitApps;
	}


	
	
	
	
	
	
}
