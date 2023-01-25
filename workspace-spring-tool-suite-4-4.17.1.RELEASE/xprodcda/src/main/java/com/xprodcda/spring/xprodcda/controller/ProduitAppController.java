package com.xprodcda.spring.xprodcda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xprodcda.spring.xprodcda.dao.ProduitApproDao;

import com.xprodcda.spring.xprodcda.domain.Produit;
import com.xprodcda.spring.xprodcda.domain.ProduitApp;

@RestController
@RequestMapping
public class ProduitAppController {

	
	
@Autowired
ProduitApproDao produitApproDao;


@GetMapping("/produitsAppro")
public List<ProduitApp> getAllProduitsApps(@Validated @RequestBody(required = false) ProduitApp produitApp) {
	return produitApproDao.getProduitsApps();
}

@PostMapping("/produitsAppro")
public ProduitApp createProduitApp(@Validated @RequestBody(required = false) ProduitApp produitApp) {
	return produitApproDao.saveProduitApp(produitApp);
}

@GetMapping("/produitsAppro/{idProduitApp}")
public ResponseEntity findProduitAppById(@PathVariable(name="idProduitApp") Long idProduitApp) {
	if( idProduitApp == null) {
		return ResponseEntity.badRequest().body("Cannot retrieve productApp with ID");
	}
	Produit produitApp = produitApproDao.getProduitAppById(idProduitApp);
	if (produitApp == null) {
		return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok().body(produitApp);
}

@PutMapping("/produitsAppro/{idProduitApp}")
public ResponseEntity<ProduitApp> updateProduit(@Validated @PathVariable(name = "idproduitApp")Long idProduit, @RequestBody(required = false)ProduitApp produitApp) {
	if(produitApp == null) {
		return ResponseEntity.notFound().build();
	}
	produitApp.setId(idProduit);
	produitApproDao.updateProduitApp(produitApp);
	return ResponseEntity.ok().body(produitApp);
}

// delete

@DeleteMapping("/produitsAppro/{idProduitApp}")
public ResponseEntity<ProduitApp> deleteProduitApp(@Validated @PathVariable(name = "idProduitApp")Long idProduit){
	ProduitApp produitApp = produitApproDao.getProduitAppById(idProduit);
	if(produitApp == null) {
		return ResponseEntity.notFound().build();
	}
	produitApproDao.deleteProduitApp(produitApp);
	return ResponseEntity.ok().body(produitApp);
}
}
