package com.xprod.spring.xprod.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprod.spring.xprod.domain.Fournisseur;
import com.xprod.spring.xprod.repository.IFournisseurRepository;

@Service
public class FournisseurDao {
	@Autowired
	IFournisseurRepository fournisseurRepository;
	
	// Liste de fournisseurs
			public List<Fournisseur> getFournisseurs() {
				return fournisseurRepository.findAll();
				
			}
			
			//Save
			public Fournisseur saveFournisseur(Fournisseur fournisseur) {
				return fournisseurRepository.save(fournisseur);
				
			}
			
			// get a Fournisseur 
			public Fournisseur getFournisseurByID(Long idFournisseur) {
				return fournisseurRepository.findById(idFournisseur).get();
			}
			
			
			// Delete a Fournisseur
			
			public void deleteFournisseur(Fournisseur fournisseur) {
				fournisseurRepository.delete(fournisseur);
			
			}

}
