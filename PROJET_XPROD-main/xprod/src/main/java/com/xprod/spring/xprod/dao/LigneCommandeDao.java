package com.xprod.spring.xprod.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprod.spring.xprod.domain.LigneCommande;
import com.xprod.spring.xprod.repository.ILigneCommandeRepository;


@Service

public class LigneCommandeDao {
	@Autowired
	ILigneCommandeRepository ligneCommandeRepository;
	
	// Liste de produits
	public List<LigneCommande> getLignesCommandes() {
		return ligneCommandeRepository.findAll();
		
	}
	
	//Save
	public LigneCommande saveLigneCommande(LigneCommande ligneCommande) {
		return ligneCommandeRepository.save(ligneCommande);
		
	}
	
	// get a Produit 
	public LigneCommande getLigneCommandeByID(Long idLigneCommande) {
		return ligneCommandeRepository.findById(idLigneCommande).get();
	}
	
	
	// Delete a produit
	
	public void deleteLigneCommande(LigneCommande ligneCommande) {
		ligneCommandeRepository.delete(ligneCommande);
	
	}
	

}
