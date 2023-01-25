package com.xprodcda.spring.xprodcda.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprodcda.spring.xprodcda.domain.Produit;
import com.xprodcda.spring.xprodcda.domain.ProduitApp;
import com.xprodcda.spring.xprodcda.repository.IProduitApproRepository;


@Service
public class ProduitApproDao {

	@Autowired
	IProduitApproRepository produitApproRepository;

	// Liste de produits appro

	public List<ProduitApp>getProduitsApps() {
		return produitApproRepository.findAll();
	}

	// save produit
		
	public ProduitApp saveProduitApp(ProduitApp produitApp) {	
		return produitApproRepository.save(produitApp);
	}

	// get produit

	public ProduitApp getProduitAppById(Long idProd) {
		return produitApproRepository.findById(idProd).get();
	}

	// delete produit

	public void  deleteProduitApp(ProduitApp produitApp) {
		produitApproRepository.delete(produitApp);
	}
	
	// update produit

	public ProduitApp updateProduitApp(ProduitApp produitApp) {
		return produitApproRepository.save(produitApp);
	}
	
}
