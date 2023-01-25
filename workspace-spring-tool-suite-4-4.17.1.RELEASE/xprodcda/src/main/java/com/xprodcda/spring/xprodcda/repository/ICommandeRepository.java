package com.xprodcda.spring.xprodcda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xprodcda.spring.xprodcda.domain.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Long>{

}
