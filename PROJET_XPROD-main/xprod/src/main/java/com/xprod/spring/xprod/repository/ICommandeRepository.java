package com.xprod.spring.xprod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xprod.spring.xprod.domain.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Long>{

}
