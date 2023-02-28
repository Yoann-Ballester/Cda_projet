package com.xprod.spring.xprod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xprod.spring.xprod.dao.XprodImagesDao;
import com.xprod.spring.xprod.domain.XprodImages;

@RestController
@RequestMapping
@CrossOrigin("*")
public class XprodImagesController {
	
	@Autowired
	XprodImagesDao xprodImagesDao;
	
	@GetMapping("/xprodImagess")
	public List<XprodImages> getAllXprodImagess(@Validated @RequestBody(required = false) XprodImages xprodImages) {
		return xprodImagesDao.getXprodImages();
		
	}
	
	@PostMapping("/xprodImagess")
	public XprodImages createXprodImages(@Validated @RequestBody(required = false) XprodImages xprodImages) {
		return xprodImagesDao.saveXprodImages(xprodImages);
		
	}
	
	@GetMapping("/xprodImagess/{idXprodImages}")
	public ResponseEntity findXprodImagesById(@PathVariable(name = "idXprodImages")Long idXprodImages){
		if (idXprodImages == null) {
			return ResponseEntity.badRequest().body("Je ne trouve pas le xprodImages avec son ID");
		}
		
		XprodImages xprodImages = xprodImagesDao.getXprodImagesByID(idXprodImages);
		
		if (xprodImages == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(xprodImages); 
		
	}
	
	@PutMapping("/xprodImagess/{idXprodImages}")
	public ResponseEntity<XprodImages> updateXprodImages (@Validated @PathVariable(name = "idXprodImages")Long idXprodImages, @RequestBody(required = false) XprodImages xprodImages) {
		if (xprodImages == null) {
			return ResponseEntity.notFound().build();
			
		}
		xprodImages.setIdImage(idXprodImages);
		xprodImagesDao.updateXprodImages(xprodImages);
		return ResponseEntity.ok().body(xprodImages);
	}
	
	@DeleteMapping("/xprodImagess/{idXprodImages}")
	public ResponseEntity<XprodImages> deleteXprodImages (@Validated @PathVariable(name = "idXprodImages")Long idXprodImages) {
		
		XprodImages xprodImages = xprodImagesDao.getXprodImagesByID(idXprodImages);
		
		if (xprodImages == null) {
			return ResponseEntity.notFound().build();
		
	}
		xprodImagesDao.deleteXprodImages(xprodImages);
		return ResponseEntity.ok().body(xprodImages); 
	
	}
	

}
