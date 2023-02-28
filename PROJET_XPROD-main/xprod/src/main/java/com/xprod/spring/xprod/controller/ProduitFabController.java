package com.xprod.spring.xprod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xprod.spring.xprod.dao.ProduitFabDao;
import com.xprod.spring.xprod.domain.ProduitFab;

@RestController
@RequestMapping
@CrossOrigin("*")
public class ProduitFabController {
	
	@Autowired
	ProduitFabDao produitFabDao;
	
	@GetMapping("/produitFab")
	public List<ProduitFab> getAllProduitsFab(@Validated @RequestBody(required = false) ProduitFab produitFab) {
		return produitFabDao.getProduitsFab();
		
	}
	
	@PostMapping("/produitFab")
	public ProduitFab createProduitFab(@Validated @RequestBody(required = false) ProduitFab produitFab) {
		return produitFabDao.saveProduitFab(produitFab);
		
	}
	
	@GetMapping("/produitFab/{idProduitFab}")
	public ResponseEntity findProduitFabById(@PathVariable(name = "idProduitFab")Long idProduitFab){
		if (idProduitFab == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas le produit avec son ID");
		}
		
		ProduitFab produitFab = produitFabDao.getProduitFabByID(idProduitFab);
		
		if (produitFab == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(produitFab); 
		
	}

}
