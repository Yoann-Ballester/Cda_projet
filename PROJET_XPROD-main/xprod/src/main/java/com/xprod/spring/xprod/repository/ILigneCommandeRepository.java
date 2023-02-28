package com.xprod.spring.xprod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xprod.spring.xprod.domain.LigneCommande;

public interface ILigneCommandeRepository extends JpaRepository<LigneCommande, Long>{

}
