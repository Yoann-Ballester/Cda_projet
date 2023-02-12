package com.ezcomp.spring.ezcomp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezcomp.spring.ezcomp.domain.Processeur;
import com.ezcomp.spring.ezcomp.repository.IProcesseurRepository;

@Service
public class ProcesseurDao {

	@Autowired
	IProcesseurRepository processeurRepository;	

	// List processeur
	public List<Processeur> getProcesseurs() {
		return processeurRepository.findAll();
	}


	//save processeur
		
	public Processeur saveProcesseur(Processeur processeur) {	
		return processeurRepository.save(processeur);
	}

	//get processeur

	public Processeur getProcesseurById(Long id) {
		return processeurRepository.findById(id).get();
	}

	//delete processeur

	public void  deleteProcesseur(Processeur processeur) {
		processeurRepository.delete(processeur);
	}

}
