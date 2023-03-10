package com.xprodcda.spring.xprodcda.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprodcda.spring.xprodcda.domain.Produit;
import com.xprodcda.spring.xprodcda.repository.IProduitRepository;

@Service
public class ProduitDao {
@Autowired
IProduitRepository produitRepository;

// Liste de produits

public List<Produit>getProduits() {
	return produitRepository.findAll();
}

// save produit
	
public Produit saveProduit(Produit produit) {	
	return produitRepository.save(produit);
}

// get produit

public Produit getProduitById(Long idProd) {
	return produitRepository.findById(idProd).get();
}

// delete produit

public void  deleteProduit(Produit produit) {
	produitRepository.delete(produit);
}

// update produit

public Produit updateProduit(Produit produit) {
	return produitRepository.save(produit);
}

}
