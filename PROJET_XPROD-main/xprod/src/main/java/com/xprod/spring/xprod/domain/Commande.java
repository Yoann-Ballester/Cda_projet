package com.xprod.spring.xprod.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity 
@Table(name="COMMANDE")
public class Commande implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMANDE_ID")
	private Long idCommande;
	@Column(name = "DATECOMMANDE", nullable= false)
	private String dateCommande;
	@Column(name = "FRAISPORTCOMMANDE")
	private double fraisPortCommande;
	
	
	public Long getIdCommande() {
		return idCommande;
	}
	public String getDateCommande() {
		return dateCommande;
	}
	public double getFraisPortCommande() {
		return fraisPortCommande;
	}
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}
	public void setFraisPortCommande(double fraisPortCommande) {
		this.fraisPortCommande = fraisPortCommande;
	}
	public Commande(Long idCommande, String dateCommande, double fraisPortCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.fraisPortCommande = fraisPortCommande;
	}
	public Commande() {
		super();
	}
	
	
	
	
	
	
	
	
	

}
