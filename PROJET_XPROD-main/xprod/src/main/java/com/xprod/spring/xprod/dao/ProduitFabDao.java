package com.xprod.spring.xprod.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xprod.spring.xprod.domain.ProduitFab;
import com.xprod.spring.xprod.repository.IProduitFabRepository;


@Service
public class ProduitFabDao {
	@Autowired
	IProduitFabRepository produitFabRepository;
	
	// Liste de produits
		public List<ProduitFab> getProduitsFab() {
			return produitFabRepository.findAll();
			
		}
		
		//Save
		public ProduitFab saveProduitFab(ProduitFab produitFab) {
			return produitFabRepository.save(produitFab);
			
		}
		
		// get a Produit 
		public ProduitFab getProduitFabByID(Long idProduitFab) {
			return produitFabRepository.findById(idProduitFab).get();
		}
		
		
		// Delete a produit
		
		public void deleteProduitFab(ProduitFab produitFab) {
			produitFabRepository.delete(produitFab);
		
		}
	

}
