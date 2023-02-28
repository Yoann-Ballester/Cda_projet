package com.xprod.spring.xprod.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprod.spring.xprod.domain.Commande;

import com.xprod.spring.xprod.repository.ICommandeRepository;


@Service

public class CommandeDao {
	@Autowired
	ICommandeRepository commandeRepository;
	
	// Liste de produits
	public List<Commande> getCommandes() {
		return commandeRepository.findAll();
		
	}
	
	//Save
	public Commande saveCommandes(Commande commande) {
		return commandeRepository.save(commande);
		
	}
	
	// get a Produit 
	public Commande getCommandeByID(Long idcommande) {
		return commandeRepository.findById(idcommande).get();
	}
	
	
	// Delete a produit
	
	public void deleteCommande(Commande commande) {
		commandeRepository.delete(commande);
	
	}
	

}
