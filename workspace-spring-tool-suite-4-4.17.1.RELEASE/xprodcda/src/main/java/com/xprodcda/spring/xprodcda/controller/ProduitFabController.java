package com.xprodcda.spring.xprodcda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xprodcda.spring.xprodcda.dao.ProduitFabDao;
import com.xprodcda.spring.xprodcda.domain.Produit;
import com.xprodcda.spring.xprodcda.domain.ProduitFab;

@RestController
@RequestMapping
public class ProduitFabController {

	
	
@Autowired
ProduitFabDao produitFabDao;


@GetMapping("/produitsfab")
public List<ProduitFab> getAllProduitsFabs(@Validated @RequestBody(required = false) ProduitFab produitFabi) {
	return produitFabDao.getProduitsFab();
}

@PostMapping("/produitsfab")
public ProduitFab createProduitFab(@Validated @RequestBody(required = false) ProduitFab produitFab) {
	return produitFabDao.saveProduitFab(produitFab);
}

@GetMapping("/produitsfab/{idProduitFab}")
public ResponseEntity findProduitFabById(@PathVariable(name="idProduitFab") Long idProduitfab) {
	if( idProduitfab == null) {
		return ResponseEntity.badRequest().body("Cannot retrieve produitFab with ID");
	}
	Produit produitFab = produitFabDao.getProduitFabById(idProduitfab);
	if (produitFab == null) {
		return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok().body(produitFab);
}
}
