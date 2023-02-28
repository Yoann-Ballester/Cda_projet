package com.xprod.spring.xprod.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name="LIGNECOMMANDE")
public class LigneCommande implements Serializable {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LIGNECOMMANDE_ID")
	private Long ligneCommande_id;
	@Column(name = "QTELIGNECOMMANDE")
	private int qteLigneCommande;
	@Column(name = "DATELIVRAISONLIGNECOMMANDE")
	private String dateLivraisonLigneCommande;
	@Column(name = "PRIXUNITAIREHTLIGNECOMMANDE")
	private double prixUnitaireHTLigneCommande;
	
	@ManyToOne
	@JoinColumn(name="idCommande")
	private Commande commande;

	public Long getLigneCommande_id() {
		return ligneCommande_id;
	}

	public int getQteLigneCommande() {
		return qteLigneCommande;
	}

	public String getDateLivraisonLigneCommande() {
		return dateLivraisonLigneCommande;
	}

	public double getPrixUnitaireHTLigneCommande() {
		return prixUnitaireHTLigneCommande;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setLigneCommande_id(Long ligneCommande_id) {
		this.ligneCommande_id = ligneCommande_id;
	}

	public void setQteLigneCommande(int qteLigneCommande) {
		this.qteLigneCommande = qteLigneCommande;
	}

	public void setDateLivraisonLigneCommande(String dateLivraisonLigneCommande) {
		this.dateLivraisonLigneCommande = dateLivraisonLigneCommande;
	}

	public void setPrixUnitaireHTLigneCommande(double prixUnitaireHTLigneCommande) {
		this.prixUnitaireHTLigneCommande = prixUnitaireHTLigneCommande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public LigneCommande(Long ligneCommande_id, int qteLigneCommande, String dateLivraisonLigneCommande,
			double prixUnitaireHTLigneCommande, Commande commande) {
		super();
		this.ligneCommande_id = ligneCommande_id;
		this.qteLigneCommande = qteLigneCommande;
		this.dateLivraisonLigneCommande = dateLivraisonLigneCommande;
		this.prixUnitaireHTLigneCommande = prixUnitaireHTLigneCommande;
		this.commande = commande;
	}

	public LigneCommande() {
		super();
	}
	
	
	
	
	

}
