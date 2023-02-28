package com.xprod.spring.xprod.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="PRODUITAPPRO")
public class ProduitAppro extends Produit{
	
	@Column(name = "PRIXACHATUNITAIREHTMO")
	private Long prixAchatUnitaireHTMoyen;
	@Column(name = "REFEXTERNEPRODUIT")
	private String refExterneProduit;
	@Column(name = "PRIXAPPROHTPRODUIT")
	private double prixApproHTProduit;
	
	
	@ManyToMany (fetch = FetchType.LAZY, mappedBy = "produitAppros")
	private List<Fournisseur> fournisseurs = new ArrayList<>();
	
	@OneToMany(mappedBy = "ligneCommande_id", fetch = FetchType.LAZY)
	private List<LigneCommande> ligneCommandes = new ArrayList<>();
	

	public Long getPrixAchatUnitaireHTMoyen() {
		return prixAchatUnitaireHTMoyen;
	}

	public String getRefExterneProduit() {
		return refExterneProduit;
	}

	public double getPrixApproHTProduit() {
		return prixApproHTProduit;
	}

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public List<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}

	public void setPrixAchatUnitaireHTMoyen(Long prixAchatUnitaireHTMoyen) {
		this.prixAchatUnitaireHTMoyen = prixAchatUnitaireHTMoyen;
	}

	public void setRefExterneProduit(String refExterneProduit) {
		this.refExterneProduit = refExterneProduit;
	}

	public void setPrixApproHTProduit(double prixApproHTProduit) {
		this.prixApproHTProduit = prixApproHTProduit;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

	public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}

	public ProduitAppro(Long idProduit, String refInterneProduit, String designationProduit, String descriptifProduit,
			double prixVenteUProduit, String imgProduitString, Long prixAchatUnitaireHTMoyen, String refExterneProduit,
			double prixApproHTProduit, List<Fournisseur> fournisseurs, List<LigneCommande> ligneCommandes) {
		super(idProduit, refInterneProduit, designationProduit, descriptifProduit, prixVenteUProduit, imgProduitString);
		this.prixAchatUnitaireHTMoyen = prixAchatUnitaireHTMoyen;
		this.refExterneProduit = refExterneProduit;
		this.prixApproHTProduit = prixApproHTProduit;
		this.fournisseurs = fournisseurs;
		this.ligneCommandes = ligneCommandes;
	}

	public ProduitAppro() {
		super();
		
	}

	public ProduitAppro(Long idProduit, String refInterneProduit, String designationProduit, String descriptifProduit,
			double prixVenteUProduit, String imgProduitString) {
		super(idProduit, refInterneProduit, designationProduit, descriptifProduit, prixVenteUProduit, imgProduitString);
		
	}

	
	


	
	
	
	

}
