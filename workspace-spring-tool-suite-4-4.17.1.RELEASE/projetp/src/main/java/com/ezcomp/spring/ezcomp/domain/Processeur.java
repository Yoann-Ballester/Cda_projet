package com.ezcomp.spring.ezcomp.domain;

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
@Table(name = "processeur")
public class Processeur extends Produit implements Serializable {

	private int guarantie;
	private String typeProc;
	private int frequenceProc;
	private int nbCoeur;
//	@ManyToOne @JoinColumn(name="idLigneCmd", nullable = false)
//	private LigneDeCommande ligneDeCommande;

	public Processeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Processeur(Long id, String ref, String designation, float prixUHT, String img, int guarantie,
			String typeProc, int frequenceProc, int nbCoeur) {
		super(id, ref, designation, prixUHT, img);
		this.guarantie = guarantie;
		this.typeProc = typeProc;
		this.frequenceProc = frequenceProc;
		this.nbCoeur = nbCoeur;
	}

	public int getGuarantie() {
		return guarantie;
	}

	public void setGuarantie(int guarantie) {
		this.guarantie = guarantie;
	}

	public String getTypeProc() {
		return typeProc;
	}

	public void setTypeProc(String typeProc) {
		this.typeProc = typeProc;
	}

	public int getFrequenceProc() {
		return frequenceProc;
	}

	public void setFrequenceProc(int frequenceProc) {
		this.frequenceProc = frequenceProc;
	}

	public int getNbCoeur() {
		return nbCoeur;
	}

	public void setNbCoeur(int nbCoeur) {
		this.nbCoeur = nbCoeur;
	}

}
