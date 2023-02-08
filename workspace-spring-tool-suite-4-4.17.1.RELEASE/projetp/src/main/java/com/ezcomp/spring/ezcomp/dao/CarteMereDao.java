package com.ezcomp.spring.ezcomp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezcomp.spring.ezcomp.domain.CarteMere;
import com.ezcomp.spring.ezcomp.repository.ICarteMereRepository;

@Service
public class CarteMereDao {

	@Autowired
	ICarteMereRepository carteMereRepository;	

	// List carteMere
	public List<CarteMere> getCarteMeres() {
		return carteMereRepository.findAll();
	}


	//save carteMere
		
	public CarteMere saveCarteMere(CarteMere carteMere) {	
		return carteMereRepository.save(carteMere);
	}

	//get carteMere

	public CarteMere getCarteMereById(Long id) {
		return carteMereRepository.findById(id).get();
	}

	//delete carteMere

	public void  deleteCarteMere(CarteMere carteMere) {
		carteMereRepository.delete(carteMere);
	}

}
