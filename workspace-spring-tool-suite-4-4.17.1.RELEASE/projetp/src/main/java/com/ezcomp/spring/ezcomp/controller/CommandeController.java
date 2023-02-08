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
import com.ezcomp.spring.ezcomp.domain.Commande;

@RestController
@RequestMapping
public class CommandeController {
	
	
		
	@Autowired
	CommandeDao commandeDao;


	@GetMapping("/commandes")
	public List<Commande> getAllCommandes(@Validated @RequestBody(required = false) Commande commande) {
		return commandeDao.getCommande();
	}

	@PostMapping("/commandes")
	public Commande createCommande(@Validated @RequestBody(required = false) Commande commande) {
		return commandeDao.saveCommande(commande);
	}

	@GetMapping("/commandes/{idCommande}")
	public ResponseEntity findCommandeById(@PathVariable(name="idCommande") Long idCommande) {
		if( idCommande == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve commande with ID");
		}
		Commande commande = commandeDao.getCommandeById(idCommande);
		if (commande == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(commande);
	}
}
