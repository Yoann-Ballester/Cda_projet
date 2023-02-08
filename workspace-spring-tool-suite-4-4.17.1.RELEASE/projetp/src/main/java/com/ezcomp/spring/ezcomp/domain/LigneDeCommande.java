package com.ezcomp.spring.ezcomp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LigneDeCommande")
public class LigneDeCommande implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idLigneCmd")
	private Long id;
	@Column(name = "qteLigneCmd")
	private int qteLigneCmd;
	@Column(name = "dateLivrCmd")
	private String dateLivreCmd;
	@Column(name = "prixUHTLigneCmd")
	private double prixUHTLigneCmd;
	
	
//	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//	private List<CarteGraphique> carteGraphiques = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//	private List<CarteMere> carteMeres = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//	private List<Processeur> processeurs = new ArrayList<>();
	
	
	


	public LigneDeCommande(Long idLigneCmd, int qteLigneCmd, String dateLivreCmd, double prixUHTLigneCmd) {
		super();
		this.id = idLigneCmd;
		this.qteLigneCmd = qteLigneCmd;
		this.dateLivreCmd = dateLivreCmd;
		this.prixUHTLigneCmd = prixUHTLigneCmd;
	}

	public LigneDeCommande() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getIdLigneCmd() {
		return id;
	}
	public void setIdLigneCmd(Long idLigneCmd) {
		this.id = idLigneCmd;
	}
	public int getQteLigneCmd() {
		return qteLigneCmd;
	}
	public void setQteLigneCmd(int qteLigneCmd) {
		this.qteLigneCmd = qteLigneCmd;
	}
	public String getDateLivreCmd() {
		return dateLivreCmd;
	}
	public void setDateLivreCmd(String dateLivreCmd) {
		this.dateLivreCmd = dateLivreCmd;
	}
	public double getPrixUHTLigneCmd() {
		return prixUHTLigneCmd;
	}
	public void setPrixUHTLigneCmd(double prixUHTLigneCmd) {
		this.prixUHTLigneCmd = prixUHTLigneCmd;
	}

//	public List<CarteGraphique> getCarteGraphiques() {
//		return carteGraphiques;
//	}
//
//	public void setCarteGraphiques(List<CarteGraphique> carteGraphiques) {
//		this.carteGraphiques = carteGraphiques;
//	}
//
//	public List<CarteMere> getCarteMeres() {
//		return carteMeres;
//	}
//
//	public void setCarteMeres(List<CarteMere> carteMeres) {
//		this.carteMeres = carteMeres;
//	}
//
//	public List<Processeur> getProcesseurs() {
//		return processeurs;
//	}
//
//	public void setProcesseurs(List<Processeur> processeurs) {
//		this.processeurs = processeurs;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}