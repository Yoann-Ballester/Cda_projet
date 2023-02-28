package com.xprod.spring.xprod.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprod.spring.xprod.domain.ProduitAppro;
import com.xprod.spring.xprod.repository.IProduitApproRepository;


@Service
public class ProduitApproDao {
	
	@Autowired
	IProduitApproRepository produitApproRepository;
	
	// Liste de produits
		public List<ProduitAppro> getProduitsAppro() {
			return produitApproRepository.findAll();
			
		}
		
		//Save
		public ProduitAppro saveProduitAppro(ProduitAppro produitAppro) {
			return produitApproRepository.save(produitAppro);
			
		}
		
		// get a Produit Appro 
		public ProduitAppro getProduitApproByID(Long idProduitAppro) {
			return produitApproRepository.findById(idProduitAppro).get();
		}
		
		
		// Delete a produit Appro
		
		public void deleteProduitAppro(ProduitAppro produitAppro) {
			produitApproRepository.delete(produitAppro);
		
		}

}
