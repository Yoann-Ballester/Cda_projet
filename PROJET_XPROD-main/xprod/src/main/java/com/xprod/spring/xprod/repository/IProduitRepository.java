package com.xprod.spring.xprod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xprod.spring.xprod.domain.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Long>{

}
