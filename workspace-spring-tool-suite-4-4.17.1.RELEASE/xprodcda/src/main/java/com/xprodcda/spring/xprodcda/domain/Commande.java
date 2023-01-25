package com.xprodcda.spring.xprodcda.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "commande")
public class Commande implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idCmd")
	private Long id;
	@Column(name = "dateCmd", nullable = false)
	private String date;
	@Column(name = "fraisLivrCmd")
	private double fraisLivrCmd;
	

	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
	private List<LigneDeCommande> ligneDeCommandes = new ArrayList<>();
	
	
	public Commande(Long id, String date, double fraisLivrCmd) {
		super();
		this.id = id;
		this.date = date;
		this.fraisLivrCmd = fraisLivrCmd;
	}


	

	public Commande(Long id, String date, double fraisLivrCmd, List<LigneDeCommande> ligneDeCommandes) {
		super();
		this.id = id;
		this.date = date;
		this.fraisLivrCmd = fraisLivrCmd;
		this.ligneDeCommandes = ligneDeCommandes;
	}
	
	




	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public double getFraisLivrCmd() {
		return fraisLivrCmd;
	}



	public void setFraisLivrCmd(double fraisLivrCmd) {
		this.fraisLivrCmd = fraisLivrCmd;
	}



	public List<LigneDeCommande> getLigneDeCommandes() {
		return ligneDeCommandes;
	}



	public void setLigneDeCommandes(List<LigneDeCommande> ligneDeCommandes) {
		this.ligneDeCommandes = ligneDeCommandes;
	}
	
	
	
	
}
