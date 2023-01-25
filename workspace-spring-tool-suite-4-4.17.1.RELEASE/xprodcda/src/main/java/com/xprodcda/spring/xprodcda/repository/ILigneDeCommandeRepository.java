package com.xprodcda.spring.xprodcda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xprodcda.spring.xprodcda.domain.LigneDeCommande;

public interface ILigneDeCommandeRepository extends JpaRepository<LigneDeCommande, Long>{

}
