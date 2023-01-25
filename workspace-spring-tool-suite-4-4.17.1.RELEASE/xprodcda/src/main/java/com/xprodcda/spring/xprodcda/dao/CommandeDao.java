package com.xprodcda.spring.xprodcda.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprodcda.spring.xprodcda.domain.Commande;

import com.xprodcda.spring.xprodcda.repository.ICommandeRepository;

@Service
public class CommandeDao {
@Autowired
ICommandeRepository commandeRepository;


// List commande
public List<Commande> getCommande() {
	return commandeRepository.findAll();
}


//save commande
	
public Commande saveCommande(Commande commande) {	
	return commandeRepository.save(commande);
}

//get commande

public Commande getCommandeById(Long idCom) {
	return commandeRepository.findById(idCom).get();
}

//delete Commande

public void  deleteCommande(Commande commande) {
	commandeRepository.delete(commande);
}


}
