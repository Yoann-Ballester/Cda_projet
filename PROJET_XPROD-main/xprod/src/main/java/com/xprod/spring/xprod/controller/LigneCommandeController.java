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

import com.xprod.spring.xprod.dao.LigneCommandeDao;
import com.xprod.spring.xprod.domain.LigneCommande;


@RestController
@RequestMapping
@CrossOrigin("*")
public class LigneCommandeController {
	
	@Autowired
	LigneCommandeDao ligneCommandeDao;
	
	@GetMapping("/lignesCommandes")
	public List<LigneCommande> getAllLignesCommandes(@Validated @RequestBody(required = false) LigneCommande ligneCommande) {
		return ligneCommandeDao.getLignesCommandes();
		
	}
	
	@PostMapping("/lignesCommandes")
	public LigneCommande createligneCommande(@Validated @RequestBody(required = false) LigneCommande ligneCommande) {
		return ligneCommandeDao.saveLigneCommande(ligneCommande);
		
	}
	
	@GetMapping("/lignesCommandes/{idLigneCommande}")
	public ResponseEntity findFournisseurById(@PathVariable(name = "idLigneCommande")Long idLigneCommande){
		if (idLigneCommande == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas la ligne de commande avec son ID");
		}
		
		LigneCommande ligneCommande = ligneCommandeDao.getLigneCommandeByID(idLigneCommande);
		
		if (ligneCommande == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(ligneCommande); 
		
	}

}
