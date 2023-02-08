package com.ezcomp.spring.ezcomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezcomp.spring.ezcomp.domain.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Long>{
	
}
