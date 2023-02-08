package com.ezcomp.spring.ezcomp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezcomp.spring.ezcomp.domain.LigneDeCommande;
import com.ezcomp.spring.ezcomp.repository.ILigneDeCommandeRepository;


@Service
public class LigneDeCommandeDao {
	@Autowired
	ILigneDeCommandeRepository ligneDeCommandeRepository;	

	// List ligneDeCommande
	public List<LigneDeCommande> getLigneDeCommandes() {
		return ligneDeCommandeRepository.findAll();
	}


	//save ligneDeCommande
		
	public LigneDeCommande saveLigneDeCommande(LigneDeCommande ligneDeCommande) {	
		return ligneDeCommandeRepository.save(ligneDeCommande);
	}

	//get ligneDeCommande

	public LigneDeCommande getLigneDeCommandeById(Long id) {
		return ligneDeCommandeRepository.findById(id).get();
	}

	//delete ligneDeCommande

	public void  deleteLigneDeCommande(LigneDeCommande ligneDeCommande) {
		ligneDeCommandeRepository.delete(ligneDeCommande);
	}

}
