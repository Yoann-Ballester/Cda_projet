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

import com.xprod.spring.xprod.dao.FournisseurDao;
import com.xprod.spring.xprod.domain.Fournisseur;


@RestController
@RequestMapping
@CrossOrigin("*")
public class FournisseurController {
	
	@Autowired
	FournisseurDao fournisseurDao;
	
	@GetMapping("/fournisseurs")
	public List<Fournisseur> getAllFournisseurs(@Validated @RequestBody(required = false) Fournisseur fournisseur) {
		return fournisseurDao.getFournisseurs();
		
	}
	
	@PostMapping("/fournisseurs")
	public Fournisseur createFournisseur(@Validated @RequestBody(required = false) Fournisseur fournisseur) {
		return fournisseurDao.saveFournisseur(fournisseur);
		
	}
	
	@GetMapping("/fournisseurs/{idFournisseur}")
	public ResponseEntity findFournisseurById(@PathVariable(name = "idFournisseur")Long idFournisseur){
		if (idFournisseur == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas le fournisseur avec son ID");
		}
		
		Fournisseur fournisseur = fournisseurDao.getFournisseurByID(idFournisseur);
		
		if (fournisseur == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(fournisseur); 
		
	}

}
