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

import com.xprod.spring.xprod.dao.CommandeDao;

import com.xprod.spring.xprod.domain.Commande;

@RestController
@RequestMapping
@CrossOrigin("*")
public class CommandeController {

	@Autowired
	CommandeDao commandeDao;

	@GetMapping("/commandes")
	public List<Commande> getAllCommandes(@Validated @RequestBody(required = false) Commande commande) {
		return commandeDao.getCommandes();

	}

	@PostMapping("/commandes")
	public Commande createCommande(@Validated @RequestBody(required = false) Commande commande) {
		return commandeDao.saveCommandes(commande);

	}

	@GetMapping("/commandes/{idCommande}")
	public ResponseEntity findProduitById(@PathVariable(name = "idCommande") Long idCommande) {
		if (idCommande == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas la commande avec son ID");
		}

		Commande commande = commandeDao.getCommandeByID(idCommande);

		if (commande == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(commande);
	}
}