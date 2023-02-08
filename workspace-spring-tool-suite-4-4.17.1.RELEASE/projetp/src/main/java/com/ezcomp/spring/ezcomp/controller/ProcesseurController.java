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

import com.ezcomp.spring.ezcomp.dao.ProcesseurDao;
import com.ezcomp.spring.ezcomp.domain.Processeur;


@RestController
@RequestMapping
public class ProcesseurController {

	@Autowired
	ProcesseurDao processeurDao;


	@GetMapping("/processeurs")
	public List<Processeur> getAllProcesseurs(@Validated @RequestBody(required = false) Processeur processeur) {
		return processeurDao.getProcesseurs();
	}

	@PostMapping("/processeurs")
	public Processeur createProcesseur(@Validated @RequestBody(required = false) Processeur processeur) {
		return processeurDao.saveProcesseur(processeur);
	}

	@GetMapping("/processeurs/{idproc}")
	public ResponseEntity findProcesseurById(@PathVariable(name="idproc") Long idproc) {
		if( idproc == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve \"processeur\" with ID");
		}
		Processeur processeur = processeurDao.getProcesseurById(idproc);
		if (processeur == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(processeur);
	}
}
