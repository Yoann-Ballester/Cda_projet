package com.ezcomp.spring.ezcomp.controller;

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

import com.ezcomp.spring.ezcomp.dao.CommandeDao;
import com.ezcomp.spring.ezcomp.dao.LigneDeCommandeDao;
import com.ezcomp.spring.ezcomp.domain.Commande;
import com.ezcomp.spring.ezcomp.domain.LigneDeCommande;



@RestController
@RequestMapping
public class LigneDeCommandeController {

	@Autowired
	LigneDeCommandeDao ligneDeCommandeDao;


	@GetMapping("/lignesdecommandes")
	public List<LigneDeCommande> getAllLigneDeCommandes(@Validated @RequestBody(required = false) LigneDeCommande ligneDeCommande) {
		return ligneDeCommandeDao.getLigneDeCommandes();
	}

	@PostMapping("/lignesdecommandes")
	public LigneDeCommande createLigneDeCommande(@Validated @RequestBody(required = false) LigneDeCommande ligneDeCommande) {
		return ligneDeCommandeDao.saveLigneDeCommande(ligneDeCommande);
	}

	@GetMapping("/lignesdecommandes/{idlignecmd}")
	public ResponseEntity findLigneDeCommandeById(@PathVariable(name="idlignecmd") Long idlignecmd) {
		if( idlignecmd == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve ligne de commande with ID");
		}
		LigneDeCommande ligneDeCommande = ligneDeCommandeDao.getLigneDeCommandeById(idlignecmd);
		if (ligneDeCommande == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ligneDeCommande);
	}
}
