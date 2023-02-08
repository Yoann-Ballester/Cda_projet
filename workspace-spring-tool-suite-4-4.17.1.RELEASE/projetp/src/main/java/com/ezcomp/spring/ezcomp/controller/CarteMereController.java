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

import com.ezcomp.spring.ezcomp.dao.CarteMereDao;
import com.ezcomp.spring.ezcomp.domain.CarteMere;



@RestController
@RequestMapping
public class CarteMereController {

	
	@Autowired
	CarteMereDao carteMereDao;


	@GetMapping("/carteMeres")
	public List<CarteMere> getAllCarteMeres(@Validated @RequestBody(required = false) CarteMere carteMere) {
		return carteMereDao.getCarteMeres();
	}

	@PostMapping("/carteMeres")
	public CarteMere createCarteMere(@Validated @RequestBody(required = false) CarteMere carteMere) {
		return carteMereDao.saveCarteMere(carteMere);
	}

	@GetMapping("/carteMeres/{idCarteMere}")
	public ResponseEntity findCommandeById(@PathVariable(name="idCarteMere") Long idCarteMere) {
		if( idCarteMere == null) {
			return ResponseEntity.badRequest().body("Cannot retrieve \"carte mere\" with ID");
		}
		CarteMere carteMere = carteMereDao.getCarteMereById(idCarteMere);
		if (carteMere == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(carteMere);
	}
}
