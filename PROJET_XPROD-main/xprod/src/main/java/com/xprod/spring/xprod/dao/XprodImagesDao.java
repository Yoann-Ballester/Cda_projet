package com.xprod.spring.xprod.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xprod.spring.xprod.domain.Produit;
import com.xprod.spring.xprod.domain.XprodImages;
import com.xprod.spring.xprod.repository.IProduitRepository;
import com.xprod.spring.xprod.repository.IXprodImagesRepository;

@Service
public class XprodImagesDao {
	@Autowired
	IXprodImagesRepository xprodImagesRepository;
	
	// Liste de produits
	public List<XprodImages> getXprodImages() {
		return xprodImagesRepository.findAll();
		
	}
	
	//Save XprodImages
	public XprodImages saveXprodImages(XprodImages xprodImages) {
		return xprodImagesRepository.save(xprodImages);
		
	}
	
	// get a Produit by ID
	public XprodImages getXprodImagesByID(Long idImage) {
		return xprodImagesRepository.findById(idImage).get();
	}
	
	
	// Delete a XprodImages
	
	public void deleteXprodImages(XprodImages xprodImages) {
		xprodImagesRepository.delete(xprodImages);
	
	}
	
	// Update 
	
	public XprodImages updateXprodImages(XprodImages xprodImages) {
		return xprodImagesRepository.save(xprodImages);
		
	}
	
 
}