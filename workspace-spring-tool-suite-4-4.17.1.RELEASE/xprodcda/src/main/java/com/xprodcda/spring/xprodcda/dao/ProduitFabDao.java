package com.xprodcda.spring.xprodcda.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xprodcda.spring.xprodcda.domain.ProduitFab;
import com.xprodcda.spring.xprodcda.repository.IProduitFabRepository;

@Service
public class ProduitFabDao {

	@Autowired
	IProduitFabRepository produitFabRepository;
	
	// Liste de produits fabrique

	public List<ProduitFab>getProduitsFab() {
		return produitFabRepository.findAll();
	}

	// save produit fab
		
	public ProduitFab saveProduitFab(ProduitFab produitFab) {	
		return produitFabRepository.save(produitFab);
	}

	// get produit fab

	public ProduitFab getProduitFabById(Long idProdFab) {
		return produitFabRepository.findById(idProdFab).get();
	}

	// delete produit

	public void  deleteProduitFab(ProduitFab produitFab) {
		produitFabRepository.delete(produitFab);
	}
	
}
