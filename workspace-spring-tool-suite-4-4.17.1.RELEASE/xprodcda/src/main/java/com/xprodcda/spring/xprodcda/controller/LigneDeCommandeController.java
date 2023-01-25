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

import com.xprodcda.spring.xprodcda.dao.LigneDeCommandeDao;
import com.xprodcda.spring.xprodcda.domain.LigneDeCommande;



@RestController
@RequestMapping
public class LigneDeCommandeController {

	
	@Autowired
	LigneDeCommandeDao ligneDeCommandeDao;


	@GetMapping("/lignesDeCommande")
	public List<LigneDeCommande> getAllLigneDeCommandes(@Validated @RequestBody(required = false) LigneDeCommande ligneDeCommande) {
		return ligneDeCommandeDao.getLignesDeCommandes();
	}

	@PostMapping("/lignesDeCommande")
	public LigneDeCommande createLigneDeCommande(@Validated @RequestBody(required = false) LigneDeCommande ligneDeCommande) {
		return ligneDeCommandeDao.saveLigneDeCommande(ligneDeCommande);
	}

	@GetMapping("/LignesDeCommande/{idLigneDeCo}")
	public ResponseEntity findLigneDeCommandeById(@PathVariable(name="idLigneDeCommande") Long idLigneDeCommande) {
		if( idLigneDeCommande == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve ligneDeCommande with ID");
		}
		
		LigneDeCommande ligneDeCommande = ligneDeCommandeDao.getLigneDeCommandeById(idLigneDeCommande);
		if (ligneDeCommande == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ligneDeCommande);
	}
}
