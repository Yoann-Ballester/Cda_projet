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

import com.ezcomp.spring.ezcomp.dao.CarteGraphiqueDao;
import com.ezcomp.spring.ezcomp.domain.CarteGraphique;



@RestController
@RequestMapping
public class CarteGraphiqueController {
	
	@Autowired
	CarteGraphiqueDao carteGraphiqueDao;
	
	@GetMapping("/cartesgraphiques")
	public List<CarteGraphique> getAllcarCarteGraphiques(@Validated @RequestBody(required = false) CarteGraphique carteGraphique) {
		return carteGraphiqueDao.getCarteGraphiques();
	}

	@PostMapping("/cartesgraphiques")
	public CarteGraphique creaCarteGraphique(@Validated @RequestBody(required = false) CarteGraphique carteGraphique) {
		return carteGraphiqueDao.saveCarteGraphique(carteGraphique);
	}

	@GetMapping("/cartesgraphiques/{idproduit}")
	public ResponseEntity findCarteGraphiqueById(@PathVariable(name="idproduit") Long idproduit) {
		if( idproduit == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve \"carte graphique\" with ID");
		}
		CarteGraphique carteGraphique = carteGraphiqueDao.getCarteGraphiqueById(idproduit);
		if (carteGraphique == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(carteGraphique);
	}
}
