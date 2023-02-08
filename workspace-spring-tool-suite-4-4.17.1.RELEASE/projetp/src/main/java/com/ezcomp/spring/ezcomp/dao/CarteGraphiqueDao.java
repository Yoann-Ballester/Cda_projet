package com.ezcomp.spring.ezcomp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezcomp.spring.ezcomp.domain.CarteGraphique;
import com.ezcomp.spring.ezcomp.repository.ICarteGraphiqueRepository;



@Service
public class CarteGraphiqueDao {
	@Autowired
	ICarteGraphiqueRepository carteGraphiqueRepository;	

	// List carteGraph
	public List<CarteGraphique> getCarteGraphiques() {
		return carteGraphiqueRepository.findAll();
	}


	//save carteGraph
		
	public CarteGraphique saveCarteGraphique(CarteGraphique carteGraphique) {	
		return carteGraphiqueRepository.save(carteGraphique);
	}

	//get carteGraph

	public CarteGraphique getCarteGraphiqueById(Long id) {
		return carteGraphiqueRepository.findById(id).get();
	}

	//delete carteGraph

	public void  deleteCarteGraphique(CarteGraphique carteGraphique) {
		carteGraphiqueRepository.delete(carteGraphique);
	}

}
