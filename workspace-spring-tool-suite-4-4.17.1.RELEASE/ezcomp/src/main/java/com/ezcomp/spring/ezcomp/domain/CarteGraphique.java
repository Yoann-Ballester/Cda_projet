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
@Table(name = "carteGraphique")
public class CarteGraphique extends Produit implements Serializable {

	
	private String serie;
	private double ramCG;
	private double frequenceGPU;
	private String marqueChipsetGraph;
	private String typeRam;
//	@ManyToOne @JoinColumn(name="idLigneCmd", nullable = false)
//	private LigneDeCommande ligneDeCommande;

	public CarteGraphique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarteGraphique(Long id, String ref, String designation, float prixUHT, String img, String serie,
			double ramCG, double frequenceGPU, String marqueChipsetGraph, String typeRam) {
		super(id, ref, designation, prixUHT, img);
		this.serie = serie;
		this.ramCG = ramCG;
		this.frequenceGPU = frequenceGPU;
		this.marqueChipsetGraph = marqueChipsetGraph;
		this.typeRam = typeRam;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public double getRamCG() {
		return ramCG;
	}

	public void setRamCG(double ramCG) {
		this.ramCG = ramCG;
	}

	public double getFrequenceGPU() {
		return frequenceGPU;
	}

	public void setFrequenceGPU(double frequenceGPU) {
		this.frequenceGPU = frequenceGPU;
	}

	public String getMarqueChipsetGraph() {
		return marqueChipsetGraph;
	}

	public void setMarqueChipsetGraph(String marqueChipsetGraph) {
		this.marqueChipsetGraph = marqueChipsetGraph;
	}

	public String getTypeRam() {
		return typeRam;
	}

	public void setTypeRam(String typeRam) {
		this.typeRam = typeRam;
	}

}
