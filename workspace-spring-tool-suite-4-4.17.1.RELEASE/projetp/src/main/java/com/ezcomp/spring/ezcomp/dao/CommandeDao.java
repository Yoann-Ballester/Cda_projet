package com.ezcomp.spring.ezcomp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezcomp.spring.ezcomp.domain.Commande;
import com.ezcomp.spring.ezcomp.repository.ICommandeRepository;



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

public Commande getCommandeById(Long id) {
	return commandeRepository.findById(id).get();
}

//delete Commande

public void  deleteCommande(Commande commande) {
	commandeRepository.delete(commande);
}
}
