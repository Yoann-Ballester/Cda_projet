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
@Table(name = "carteMere")
public class CarteMere extends Produit implements Serializable {

	private int socketCPU;
	private String typeRam;
	private String processeurCompatible;
	private String typeChipset;
	private String viteHorlogeMemoireString;
	private String serie;
	private double capaciteMem;
//	@ManyToOne @JoinColumn(name="idLigneCmd", nullable = false)
//	private LigneDeCommande ligneDeCommande;

	public CarteMere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarteMere(Long id, String ref, String designation, float prixUHT, String img, int socketCPU, String typeRam,
			String processeurCompatible, String typeChipset, String viteHorlogeMemoireString, String serie,
			double capaciteMem) {
		super(id, ref, designation, prixUHT, img);
		this.socketCPU = socketCPU;
		this.typeRam = typeRam;
		this.processeurCompatible = processeurCompatible;
		this.typeChipset = typeChipset;
		this.viteHorlogeMemoireString = viteHorlogeMemoireString;
		this.serie = serie;
		this.capaciteMem = capaciteMem;
	}

	public int getSocketCPU() {
		return socketCPU;
	}

	public void setSocketCPU(int socketCPU) {
		this.socketCPU = socketCPU;
	}

	public String getTypeRam() {
		return typeRam;
	}

	public void setTypeRam(String typeRam) {
		this.typeRam = typeRam;
	}

	public String getProcesseurCompatible() {
		return processeurCompatible;
	}

	public void setProcesseurCompatible(String processeurCompatible) {
		this.processeurCompatible = processeurCompatible;
	}

	public String getTypeChipset() {
		return typeChipset;
	}

	public void setTypeChipset(String typeChipset) {
		this.typeChipset = typeChipset;
	}

	public String getViteHorlogeMemoireString() {
		return viteHorlogeMemoireString;
	}

	public void setViteHorlogeMemoireString(String viteHorlogeMemoireString) {
		this.viteHorlogeMemoireString = viteHorlogeMemoireString;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public double getCapaciteMem() {
		return capaciteMem;
	}

	public void setCapaciteMem(double capaciteMem) {
		this.capaciteMem = capaciteMem;
	}

}
