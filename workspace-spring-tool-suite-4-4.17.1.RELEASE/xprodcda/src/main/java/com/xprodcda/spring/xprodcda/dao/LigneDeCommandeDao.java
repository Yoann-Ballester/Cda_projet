package com.xprodcda.spring.xprodcda.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xprodcda.spring.xprodcda.domain.LigneDeCommande;
import com.xprodcda.spring.xprodcda.repository.ILigneDeCommandeRepository;

@Service
public class LigneDeCommandeDao {

	@Autowired
	ILigneDeCommandeRepository ligneDeCommandeRepository;
	

	// List Ligne de commande
	public List<LigneDeCommande> getLignesDeCommandes() {
		return ligneDeCommandeRepository.findAll();
	}


	//save ligne de commande
		
	public LigneDeCommande saveLigneDeCommande(LigneDeCommande ligneDeCommande) {	
		return ligneDeCommandeRepository.save(ligneDeCommande);
	}

	//get ligne de commande

	public LigneDeCommande getLigneDeCommandeById(Long idLigneDeCom) {
		return ligneDeCommandeRepository.findById(idLigneDeCom).get();
	}

	//delete ligne de Commande

	public void  deleteLigneDeCommande(LigneDeCommande ligneDeCommande) {
		ligneDeCommandeRepository.delete(ligneDeCommande);
	}
}
