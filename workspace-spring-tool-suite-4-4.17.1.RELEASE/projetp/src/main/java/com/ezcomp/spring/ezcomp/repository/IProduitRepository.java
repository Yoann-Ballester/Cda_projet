package com.ezcomp.spring.ezcomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezcomp.spring.ezcomp.domain.Produit;

public interface IProduitRepository extends JpaRepository<Produit,Long>{

}
