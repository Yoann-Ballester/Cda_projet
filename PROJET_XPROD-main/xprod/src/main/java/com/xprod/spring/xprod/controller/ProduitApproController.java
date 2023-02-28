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

import com.xprod.spring.xprod.dao.ProduitApproDao;
import com.xprod.spring.xprod.domain.ProduitAppro;

@RestController
@RequestMapping
@CrossOrigin("*")
public class ProduitApproController {
	
	@Autowired
	ProduitApproDao produitApproDao;
	
	@GetMapping("/produitAppro")
	public List<ProduitAppro> getAllProduitsAppro(@Validated @RequestBody(required = false) ProduitAppro produitAppro) {
		return produitApproDao.getProduitsAppro();
		
	}
	
	@PostMapping("/produitAppro")
	public ProduitAppro createProduitAppro(@Validated @RequestBody(required = false) ProduitAppro produitAppro) {
		return produitApproDao.saveProduitAppro(produitAppro);
		
	}
	
	@GetMapping("/produitAppro/{idProduitAppro}")
	public ResponseEntity findProduitById(@PathVariable(name = "idProduitAppro")Long idProduitAppro){
		if (idProduitAppro == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas le produit appro avec son ID");
		}
		
		ProduitAppro produitAppro = produitApproDao.getProduitApproByID(idProduitAppro);
		
		if (produitAppro == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(produitAppro); 
		
	}

}
